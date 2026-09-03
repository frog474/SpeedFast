# SpeedFast

## Descripción

SpeedFast es un sistema de gestión de pedidos de reparto desarrollado en Java. El sistema permite administrar distintos tipos de pedidos: comida, encomiendas y pedidos express, cada uno con sus propias reglas para la asignación de repartidores y el cálculo del tiempo estimado de entrega.

En esta versión se incorpora **programación concurrente**, permitiendo simular a varios repartidores realizando entregas de manera simultánea mediante hilos en Java.

## Objetivo

Desarrollar un sistema orientado a objetos que permita gestionar distintos tipos de pedidos y simular la entrega concurrente de múltiples pedidos.

El sistema permite:

* Asignar repartidores automáticamente.
* Asignar repartidores manualmente.
* Mostrar el resumen de los pedidos.
* Calcular el tiempo estimado de entrega.
* Despachar pedidos.
* Cancelar pedidos.
* Visualizar el historial de entregas.
* Asignar múltiples pedidos a distintos repartidores.
* Ejecutar entregas de manera concurrente.

## Tecnologías utilizadas

* Java
* IntelliJ IDEA
* Programación Orientada a Objetos
* ArrayList
* Runnable
* Thread
* ExecutorService
* GitHub

## Estructura del proyecto

```text
SpeedFast/
└── src/
    ├── app/
    │   └── Main.java
    │
    ├── interfaces/
    │   ├── Cancelable.java
    │   ├── Despachable.java
    │   └── Rastreable.java
    │
    ├── model/
    │   ├── Pedido.java
    │   ├── PedidoComida.java
    │   ├── PedidoEncomienda.java
    │   ├── PedidoExpress.java
    │   └── Repartidor.java
    │
    └── service/
        └── ControladorDeEnvios.java
```

## Clases principales

### Pedido

`Pedido` es una clase abstracta que contiene los atributos y comportamientos comunes de los diferentes tipos de pedidos.

Atributos principales:

* `idPedido`
* `direccionEntrega`
* `distanciaKm`
* `repartidor`

También contiene el método `mostrarResumen()` y define el método abstracto `calcularTiempoEntrega()`.

Además, posee dos versiones de `asignarRepartidor()`:

```java
asignarRepartidor()
asignarRepartidor(String nombreRepartidor)
```

Esto permite demostrar la **sobrecarga de métodos**.

### PedidoComida

Representa un pedido de comida.

Su repartidor automático es **Luis Díaz**.

El tiempo estimado de entrega se calcula mediante:

```text
15 + (2 × distancia en km)
```

### PedidoEncomienda

Representa un pedido de encomienda.

Su repartidora automática es **Daniela Tapia**.

El tiempo estimado de entrega se calcula mediante:

```text
20 + (1.5 × distancia en km)
```

También permite asignar un repartidor manualmente mediante el método sobrecargado `asignarRepartidor(String nombreRepartidor)`.

### PedidoExpress

Representa un pedido express.

Su repartidor automático es **Pedro Soto**.

El tiempo estimado depende de la distancia:

* Más de 5 km: 15 minutos.
* 5 km o menos: 10 minutos.

### Repartidor

`Repartidor` representa a un repartidor encargado de realizar una lista de pedidos.

La clase implementa:

```java
Runnable
```

Cada repartidor posee:

* `nombre`
* `pedidosAsignados`

El método `run()` recorre los pedidos asignados de manera secuencial y simula cada entrega utilizando `Thread.sleep()` con un tiempo aleatorio.

Esto permite que varios repartidores puedan realizar sus entregas simultáneamente.

## Interfaces

El proyecto utiliza tres interfaces:

### Despachable

Define el método:

```java
void despachar();
```

Permite realizar el despacho de un pedido.

### Cancelable

Define el método:

```java
void cancelar();
```

Permite cancelar un pedido.

### Rastreable

Define el método:

```java
void verHistorial();
```

Permite visualizar el historial de entregas.

## ControladorDeEnvios

`ControladorDeEnvios` implementa las interfaces:

* `Despachable`
* `Cancelable`
* `Rastreable`

Se encarga de controlar las operaciones de despacho, cancelación y consulta del historial.

Utiliza un `ArrayList<String>` para almacenar el historial de los pedidos despachados.

## Programación Orientada a Objetos

El proyecto aplica los principales conceptos de Programación Orientada a Objetos:

### Abstracción

`Pedido` es una clase abstracta que contiene los elementos comunes de todos los tipos de pedidos.

### Herencia

Las clases `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` heredan de `Pedido`.

### Sobrescritura

Las clases derivadas sobrescriben métodos como:

```java
asignarRepartidor()
calcularTiempoEntrega()
```

para adaptar su comportamiento según el tipo de pedido.

### Sobrecarga

La clase `Pedido` posee dos métodos `asignarRepartidor()` con diferentes parámetros:

```java
asignarRepartidor()
asignarRepartidor(String nombreRepartidor)
```

### Polimorfismo

Se utiliza un arreglo de tipo `Pedido` que contiene diferentes tipos de pedidos:

```java
Pedido[] pedidos = {
    pedidoComida1,
    pedidoEncomienda1,
    pedidoExpress1
};
```

Al llamar a `calcularTiempoEntrega()`, cada objeto ejecuta la implementación correspondiente a su clase.

### Interfaces

Las interfaces permiten separar las responsabilidades de despacho, cancelación y seguimiento del historial.

## Programación concurrente

En esta versión se incorpora programación multihilo para simular entregas simultáneas.

Se crean tres repartidores:

* Camila
* Luis
* Pedro

Cada repartidor recibe dos pedidos.

La ejecución se realiza mediante `ExecutorService`:

```java
ExecutorService executor = Executors.newFixedThreadPool(3);
```

Luego se ejecutan los tres repartidores:

```java
executor.execute(camila);
executor.execute(luis);
executor.execute(pedro);
```

Finalmente, se utiliza:

```java
executor.shutdown();
```

para evitar que se agreguen nuevas tareas y permitir que los repartidores finalicen sus entregas.

El programa espera hasta que todos los repartidores terminan la simulación.

## Simulación de entregas

Cada repartidor procesa sus pedidos secuencialmente dentro de su propio hilo.

El tiempo de cada entrega se simula utilizando `Thread.sleep()` con valores aleatorios.

Ejemplo:

```text
===== SIMULACIÓN DE ENTREGAS CONCURRENTES =====

[Repartidor: Camila] Entregando PedidoComida #101...
[Repartidor: Pedro] Entregando PedidoEncomienda #102...
[Repartidor: Luis] Entregando PedidoExpress #103...
[Repartidor: Luis] Pedido #103 entregado.
[Repartidor: Luis] Entregando PedidoComida #104...
[Repartidor: Camila] Pedido #101 entregado.
[Repartidor: Camila] Entregando PedidoExpress #106...
[Repartidor: Luis] Pedido #104 entregado.
[Repartidor: Pedro] Pedido #102 entregado.
[Repartidor: Pedro] Entregando PedidoEncomienda #105...
[Repartidor: Camila] Pedido #106 entregado.
[Repartidor: Pedro] Pedido #105 entregado.

===== TODAS LAS ENTREGAS FINALIZADAS =====
```

El orden de los mensajes puede variar en cada ejecución debido a la naturaleza concurrente de los hilos.

## Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en **IntelliJ IDEA**.
2. Abrir la clase `Main.java`.
3. Ejecutar el método `main`.
4. Observar en la consola la ejecución de las entregas concurrentes.

La ejecución finaliza cuando todos los repartidores han terminado sus entregas.

## Autor

Proyecto desarrollado como actividad académica de Programación Orientada a Objetos.
