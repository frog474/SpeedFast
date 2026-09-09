# SpeedFast

## Descripción

SpeedFast es un sistema de gestión de pedidos de reparto desarrollado en Java.

En esta versión se incorpora **programación concurrente y sincronización de procesos**, permitiendo simular a varios repartidores trabajando simultáneamente sobre una zona de carga compartida.

Los pedidos pasan por diferentes estados durante el proceso de entrega:

```text
PENDIENTE → EN_REPARTO → ENTREGADO
```

El sistema utiliza varios hilos para que los repartidores puedan retirar y procesar pedidos de manera concurrente, evitando que un mismo pedido sea retirado por más de un repartidor.

## Objetivo

Desarrollar un sistema orientado a objetos que permita gestionar pedidos y simular su entrega mediante múltiples hilos, utilizando mecanismos de sincronización para proteger los recursos compartidos.

El sistema permite:

* Crear pedidos.
* Almacenar pedidos en una zona de carga compartida.
* Retirar pedidos de forma segura.
* Asignar pedidos a distintos repartidores.
* Cambiar el estado de los pedidos durante el proceso de entrega.
* Ejecutar múltiples repartidores de manera concurrente.
* Simular tiempos de entrega mediante `Thread.sleep()`.
* Garantizar que cada pedido sea retirado y procesado por un único repartidor.
* Esperar la finalización de todos los procesos antes de terminar la ejecución.

## Tecnologías utilizadas

* Java
* JDK 25
* IntelliJ IDEA
* Programación Orientada a Objetos
* `Runnable`
* `Thread`
* `ExecutorService`
* `synchronized`
* `ArrayList`
* GitHub

## Estructura del proyecto

```text
SpeedFast/
└── src/
    ├── app/
    │   └── Main.java
    │
    ├── model/
    │   ├── EstadoPedido.java
    │   ├── Pedido.java
    │   └── Repartidor.java
    │
    └── service/
        └── ZonaDeCarga.java
```

## Clases principales

### Pedido

`Pedido` representa un pedido dentro del sistema.

Sus principales atributos son:

* `id`
* `direccionEntrega`
* `estado`

El estado del pedido utiliza el enum `EstadoPedido`.

La clase incluye métodos `getters`, `setters` y `toString()` para gestionar y mostrar la información de cada pedido.

El estado puede cambiar durante el proceso de entrega mediante:

```java
setEstado(EstadoPedido nuevoEstado)
```

### EstadoPedido

`EstadoPedido` es un `enum` que define los estados posibles de un pedido:

```java
PENDIENTE,
EN_REPARTO,
ENTREGADO
```

Estos estados permiten representar el ciclo de vida de cada pedido durante la simulación.

### ZonaDeCarga

`ZonaDeCarga` representa el recurso compartido entre los distintos repartidores.

Internamente utiliza una lista de pedidos:

```java
private List<Pedido> pedidos;
```

La clase posee los métodos:

```java
public synchronized void agregarPedido(Pedido p)
```

y:

```java
public synchronized Pedido retirarPedido()
```

El uso de `synchronized` permite controlar el acceso concurrente a la zona de carga.

De esta forma, cuando varios repartidores intentan retirar pedidos al mismo tiempo, solamente uno puede ejecutar la operación de retiro a la vez.

La zona de carga solamente permite retirar pedidos que se encuentren en estado:

```text
PENDIENTE
```

Una vez retirado, el pedido pasa inmediatamente a:

```text
EN_REPARTO
```

Si no quedan pedidos pendientes, el método retorna `null` y el repartidor finaliza su ejecución.

## Repartidor

`Repartidor` representa a un trabajador encargado de procesar pedidos.

La clase implementa:

```java
Runnable
```

Cada repartidor posee:

* `nombre`
* `zonaDeCarga`

Al ejecutar el método `run()`, el repartidor:

1. Intenta retirar un pedido de la zona de carga.
2. Cambia el estado del pedido a `EN_REPARTO`.
3. Muestra información del pedido retirado.
4. Simula el tiempo de entrega mediante `Thread.sleep()`.
5. Cambia el estado del pedido a `ENTREGADO`.
6. Vuelve a la zona de carga para intentar retirar otro pedido.
7. Finaliza cuando no quedan pedidos pendientes.

El uso de `Runnable` permite ejecutar cada repartidor como una tarea independiente dentro de un hilo.

## Programación concurrente

El sistema utiliza tres repartidores ejecutándose de manera concurrente:

* Juan
* Camila
* Pedro

Los tres comparten la misma instancia de `ZonaDeCarga`.

La ejecución se realiza mediante:

```java
ExecutorService executor = Executors.newFixedThreadPool(3);
```

Luego se agregan los tres repartidores al executor:

```java
executor.execute(juan);
executor.execute(camila);
executor.execute(pedro);
```

De esta forma, los tres pueden procesar pedidos simultáneamente.

Finalmente:

```java
executor.shutdown();
```

impide que se agreguen nuevas tareas y permite que los procesos actuales terminen correctamente.

El programa espera hasta que todos los repartidores hayan finalizado.

## Sincronización

La sincronización es necesaria porque la `ZonaDeCarga` es un **recurso compartido** por los tres repartidores.

Los métodos que modifican la lista de pedidos utilizan `synchronized`:

```java
public synchronized void agregarPedido(Pedido p)
```

```java
public synchronized Pedido retirarPedido()
```

Esto evita problemas de concurrencia al momento de retirar pedidos.

Por ejemplo, si dos repartidores intentan retirar un pedido al mismo tiempo, el método sincronizado garantiza que la operación se realice de manera controlada.

Una vez que un repartidor retira un pedido, este se elimina de la zona de carga y ya no puede ser retirado nuevamente por otro repartidor.

## Estados de los pedidos

Cada pedido sigue el siguiente flujo:

```text
PENDIENTE
    ↓
EN_REPARTO
    ↓
ENTREGADO
```

### PENDIENTE

El pedido se encuentra esperando en la zona de carga.

### EN_REPARTO

El pedido ya fue retirado por un repartidor y se encuentra en proceso de entrega.

### ENTREGADO

El repartidor terminó la simulación de entrega y el pedido fue entregado correctamente.

## Simulación de entregas

El tiempo de entrega se simula mediante:

```java
Thread.sleep()
```

Se utiliza un tiempo aleatorio entre 1 y 4 segundos aproximadamente para representar que cada entrega puede tardar una cantidad diferente de tiempo.

Debido a la ejecución concurrente, el orden en que aparecen los mensajes puede cambiar en cada ejecución.

Por ejemplo:

```text
[Repartidor - Juan] Retirando pedido #1...
[Repartidor - Pedro] Retirando pedido #2...
[Repartidor - Camila] Retirando pedido #3...

[Repartidor - Juan] Estado: EN_REPARTO
[Repartidor - Camila] Estado: EN_REPARTO
[Repartidor - Pedro] Estado: EN_REPARTO

[Repartidor - Juan] Entregando pedido #1...
[Repartidor - Camila] Entregando pedido #3...
[Repartidor - Pedro] Entregando pedido #2...
```

Posteriormente, los repartidores pueden retirar nuevos pedidos hasta que la zona de carga quede vacía.

## Ejemplo de ejecución

```text
===== SPEEDFAST =====

[Zona de carga inicializada]
Pedido #1 agregado. Destino: Santiago Centro
Pedido #2 agregado. Destino: Providencia
Pedido #3 agregado. Destino: Ñuñoa
Pedido #4 agregado. Destino: Recoleta
Pedido #5 agregado. Destino: Las Condes

[Repartidor - Juan] Retirando pedido #1...
[Repartidor - Pedro] Retirando pedido #2...
[Repartidor - Camila] Retirando pedido #3...

[Repartidor - Juan] Estado: EN_REPARTO
[Repartidor - Pedro] Estado: EN_REPARTO
[Repartidor - Camila] Estado: EN_REPARTO

[Repartidor - Juan] Entregando pedido #1...
[Repartidor - Pedro] Entregando pedido #2...
[Repartidor - Camila] Entregando pedido #3...

[Repartidor - Juan] Estado: ENTREGADO
[Repartidor - Juan] Retirando pedido #4...

[Repartidor - Camila] Estado: ENTREGADO
[Repartidor - Camila] Retirando pedido #5...

[Repartidor - Pedro] Estado: ENTREGADO
[Zona de carga vacía]

[Repartidor - Juan] Estado: ENTREGADO
[Zona de carga vacía]

[Repartidor - Camila] Estado: ENTREGADO
[Zona de carga vacía]

Todos los pedidos han sido entregados correctamente

Process finished with exit code 0
```

El orden exacto de los mensajes puede variar en cada ejecución debido a la naturaleza concurrente de los hilos.

## Manejo de interrupciones

Durante la simulación se controla la excepción `InterruptedException` generada por `Thread.sleep()`.

Cuando un hilo es interrumpido, se restaura su estado mediante:

```java
Thread.currentThread().interrupt();
```

y se finaliza correctamente la ejecución del repartidor.

## Principios de Programación Orientada a Objetos

El proyecto utiliza principios de Programación Orientada a Objetos para organizar las responsabilidades del sistema.

### Encapsulamiento

Los atributos de las clases se mantienen privados y se accede a ellos mediante métodos públicos.

### Responsabilidad de las clases

Cada clase tiene una responsabilidad específica:

* `Pedido`: representa y administra la información y estado de un pedido.
* `EstadoPedido`: define los estados posibles.
* `ZonaDeCarga`: administra el recurso compartido y controla el retiro de pedidos.
* `Repartidor`: ejecuta el proceso concurrente de entrega.
* `Main`: crea los objetos y coordina la ejecución del sistema.

Esta separación facilita la comprensión y el mantenimiento del código.

## Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en **IntelliJ IDEA**.
2. Abrir `src/app/Main.java`.
3. Ejecutar el método `main`.
4. Observar la salida en la consola.
5. Verificar que los tres repartidores trabajen de manera concurrente.
6. Verificar que todos los pedidos terminen en estado `ENTREGADO`.

La ejecución finaliza cuando todos los repartidores han terminado sus tareas.

## Resultado

El sistema permite comprobar el funcionamiento de múltiples hilos trabajando sobre un recurso compartido.

La utilización de `synchronized` en la zona de carga evita interferencias entre los repartidores y garantiza que un pedido retirado por un repartidor no pueda ser retirado nuevamente por otro.

Al finalizar la simulación, todos los pedidos han sido procesados y entregados correctamente.

## Autor

Proyecto desarrollado como actividad académica de Programación Orientada a Objetos y Programación Concurrente.
