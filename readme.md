# SpeedFast

## 📋 Descripción

**SpeedFast** es un sistema de gestión de pedidos de reparto desarrollado en **Java**.

En esta versión se incorpora **programación concurrente, sincronización de procesos e interfaz gráfica**, permitiendo simular repartidores trabajando sobre una zona de carga compartida y gestionar los pedidos mediante ventanas gráficas.

Los pedidos pasan por diferentes estados durante el proceso de entrega:

```text
PENDIENTE → EN_REPARTO → ENTREGADO
```

El sistema utiliza **hilos** para que los repartidores puedan retirar y procesar pedidos de manera concurrente, evitando que un mismo pedido sea retirado por más de un repartidor.

Además, la interfaz gráfica permite registrar pedidos, visualizarlos en una tabla e iniciar la simulación de sus entregas.

---

## 🎯 Objetivo

Desarrollar un sistema orientado a objetos que permita gestionar pedidos y simular su entrega mediante múltiples hilos, utilizando mecanismos de sincronización para proteger los recursos compartidos.

También se incorpora una interfaz gráfica utilizando **Java Swing** para facilitar la interacción del usuario con el sistema.

El sistema permite:

* Crear pedidos.
* Registrar pedidos mediante una interfaz gráfica.
* Almacenar pedidos en una lista en memoria.
* Visualizar pedidos mediante una tabla.
* Actualizar la información de los pedidos.
* Asignar pedidos a repartidores.
* Cambiar el estado de los pedidos durante el proceso de entrega.
* Ejecutar repartidores de manera concurrente.
* Simular tiempos de entrega mediante `Thread.sleep()`.
* Garantizar que cada pedido sea retirado y procesado por un único repartidor.
* Evitar iniciar una entrega cuando no existen pedidos pendientes.
* Esperar la finalización de los procesos antes de terminar la ejecución.

---

## 🛠️ Tecnologías utilizadas

* **Java**
* **JDK 25**
* **IntelliJ IDEA**
* **Programación Orientada a Objetos**
* **Java Swing**
* `JFrame`
* `JTable`
* `DefaultTableModel`
* `JComboBox`
* `JOptionPane`
* `Runnable`
* `Thread`
* `ExecutorService`
* `synchronized`
* `ArrayList`
* **GitHub**

---

## 📁 Estructura del proyecto

```text
SpeedFast/
└── src/
    ├── app/
    │   └── Main.java
    │
    ├── main/
    │   └── Main.java
    │
    ├── model/
    │   ├── EstadoPedido.java
    │   ├── Pedido.java
    │   └── Repartidor.java
    │
    ├── service/
    │   ├── ControladorDeEnvios.java
    │   └── ZonaDeCarga.java
    │
    └── view/
        ├── VentanaPrincipal.java
        ├── VentanaRegistroPedido.java
        └── VentanaListaPedidos.java
```

---

# 📦 Clases principales

## Pedido

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

---

## EstadoPedido

`EstadoPedido` es un `enum` que define los estados posibles de un pedido:

```java
PENDIENTE,
EN_REPARTO,
ENTREGADO
```

Estos estados permiten representar el ciclo de vida de cada pedido durante la simulación.

---

## ZonaDeCarga

`ZonaDeCarga` representa el **recurso compartido** entre los distintos repartidores.

Internamente utiliza una lista de pedidos:

```java
private List<Pedido> pedidos;
```

La clase posee los métodos:

```java
public synchronized void agregarPedido(Pedido p)
```

```java
public synchronized Pedido retirarPedido()
```

Además, cuenta con un método para comprobar si existen pedidos pendientes:

```java
public synchronized boolean hayPedidosPendientes()
```

El uso de `synchronized` permite controlar el acceso concurrente a la zona de carga.

De esta forma, cuando varios repartidores intentan retirar pedidos al mismo tiempo, solamente uno puede ejecutar la operación de retiro a la vez.

La zona de carga solamente permite retirar pedidos que se encuentren en estado:

```text
PENDIENTE
```

Una vez retirado, el pedido pasa a:

```text
EN_REPARTO
```

Si no quedan pedidos pendientes, el método retorna `null` y el repartidor finaliza su ejecución.

---

## 🚴 Repartidor

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

---

## 📋 ControladorDeEnvios

`ControladorDeEnvios` permite centralizar los pedidos registrados desde la interfaz gráfica.

Mantiene una lista de pedidos en memoria y una instancia de `ZonaDeCarga`.

Cuando se registra un nuevo pedido, este se agrega al controlador y también a la zona de carga para que pueda ser procesado por un repartidor.

De esta forma, las distintas ventanas de la interfaz gráfica trabajan utilizando los mismos datos.

---

# 🖥️ Interfaz gráfica

La interfaz gráfica fue desarrollada utilizando **Java Swing** y ventanas `JFrame`.

## VentanaPrincipal

`VentanaPrincipal` corresponde a la ventana principal del sistema.

Cuenta con botones para:

* 📝 Registrar pedido.
* 📋 Listar pedidos.
* 🚚 Iniciar entrega.

Desde esta ventana se puede acceder a las demás funcionalidades del sistema.

El botón de inicio de entrega comprueba primero si existen pedidos pendientes. Si no existen, se informa al usuario mediante un mensaje y no se inicia el repartidor.

---

## VentanaRegistroPedido

`VentanaRegistroPedido` permite registrar nuevos pedidos mediante un formulario gráfico.

El formulario contiene:

* **ID**
* **Dirección**
* **Tipo de pedido**
* **Botón Guardar**

El tipo de pedido se selecciona mediante un `JComboBox` con las opciones:

```text
Comida
Encomienda
Express
```

El sistema valida que los campos obligatorios estén completos y que el ID corresponda a un número válido.

Al guardar correctamente, el pedido se crea con estado:

```text
PENDIENTE
```

Posteriormente se agrega al `ControladorDeEnvios` y se muestra una confirmación mediante `JOptionPane`.

---

## VentanaListaPedidos

`VentanaListaPedidos` permite visualizar los pedidos registrados mediante un `JTable`.

La información de la tabla es gestionada mediante `DefaultTableModel`.

La tabla muestra:

| ID | Dirección | Estado    |
| -- | --------- | --------- |
| 44 | ddd       | PENDIENTE |

Además, cuenta con un botón **Actualizar** que permite refrescar la información mostrada.

---

# 🧵 Programación concurrente

El sistema utiliza **tres repartidores ejecutándose de manera concurrente** en la simulación original:

* **Juan**
* **Camila**
* **Pedro**

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

La interfaz gráfica también permite iniciar una entrega mediante un **hilo independiente**.

---

# 🔒 Sincronización

La sincronización es necesaria porque la `ZonaDeCarga` es un **recurso compartido** por los repartidores.

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

---

# 🔄 Estados de los pedidos

Cada pedido sigue el siguiente flujo:

```text
PENDIENTE
    ↓
EN_REPARTO
    ↓
ENTREGADO
```

### 🟡 PENDIENTE

El pedido se encuentra esperando en la zona de carga.

### 🟠 EN_REPARTO

El pedido ya fue retirado por un repartidor y se encuentra en proceso de entrega.

### 🟢 ENTREGADO

El repartidor terminó la simulación de entrega y el pedido fue entregado correctamente.

---

# ⏱️ Simulación de entregas

El tiempo de entrega se simula mediante:

```java
Thread.sleep()
```

Se utiliza un tiempo aleatorio entre **1 y 4 segundos aproximadamente** para representar que cada entrega puede tardar una cantidad diferente de tiempo.

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

---

# ▶️ Ejemplo de ejecución

La aplicación gráfica se inicia mostrando la ventana principal:

```text
SpeedFast - Gestión de Entregas
```

Desde ella se puede seleccionar:

```text
Registrar pedido
Listar pedidos
Iniciar entrega
```

Al registrar un pedido, por ejemplo:

```text
ID: 44
Dirección: ddd
Tipo: Comida
```

El sistema muestra una confirmación y el pedido queda registrado con estado `PENDIENTE`.

Al iniciar la entrega se puede observar en consola:

```text
[Repartidor - Juan] Retirando pedido #44...
[Repartidor - Juan] Estado: EN_REPARTO
[Repartidor - Juan] Entregando pedido #44...
[Repartidor - Juan] Estado: ENTREGADO
```

El pedido también puede visualizarse desde la ventana de listado.

Si no existen pedidos pendientes y se selecciona **Iniciar entrega**, el sistema muestra:

```text
No hay pedidos pendientes para entregar.
```

y no inicia una nueva entrega.

---

# ⚠️ Manejo de interrupciones

Durante la simulación se controla la excepción `InterruptedException` generada por `Thread.sleep()`.

Cuando un hilo es interrumpido, se restaura su estado mediante:

```java
Thread.currentThread().interrupt();
```

y se finaliza correctamente la ejecución del repartidor.

---

# 🧱 Principios de Programación Orientada a Objetos

El proyecto utiliza principios de **Programación Orientada a Objetos** para organizar las responsabilidades del sistema.

## Encapsulamiento

Los atributos de las clases se mantienen privados y se accede a ellos mediante métodos públicos.

## Responsabilidad de las clases

Cada clase tiene una responsabilidad específica:

| Clase                   | Responsabilidad                                                   |
| ----------------------- | ----------------------------------------------------------------- |
| `Pedido`                | Representa y administra la información y estado de un pedido.     |
| `EstadoPedido`          | Define los estados posibles.                                      |
| `ZonaDeCarga`           | Administra el recurso compartido y controla el retiro de pedidos. |
| `Repartidor`            | Ejecuta el proceso concurrente de entrega.                        |
| `ControladorDeEnvios`   | Administra los pedidos utilizados por la interfaz gráfica.        |
| `VentanaPrincipal`      | Proporciona el acceso principal a las funciones del sistema.      |
| `VentanaRegistroPedido` | Permite registrar nuevos pedidos.                                 |
| `VentanaListaPedidos`   | Permite visualizar y actualizar los pedidos.                      |
| `Main`                  | Inicia la aplicación.                                             |

Esta separación facilita la comprensión y el mantenimiento del código.

---

# ▶️ Ejecución

## 🖥️ Interfaz gráfica

Para ejecutar la aplicación gráfica:

1. Abrir el proyecto en **IntelliJ IDEA**.
2. Abrir `src/main/Main.java`.
3. Ejecutar el método `main`.
4. Se abrirá la ventana principal de SpeedFast.
5. Utilizar las opciones de registro, listado e inicio de entregas.

La aplicación gráfica se inicia mediante:

```java
package main;

import view.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
```

---

## 🧵 Simulación concurrente

La simulación original de múltiples repartidores se mantiene en:

```text
src/app/Main.java
```

Para ejecutarla:

1. Abrir `src/app/Main.java`.
2. Ejecutar el método `main`.
3. Observar la salida en la consola.
4. Verificar que los tres repartidores trabajen de manera concurrente.
5. Verificar que todos los pedidos terminen en estado `ENTREGADO`.

---

# ✅ Resultado

El sistema permite gestionar pedidos mediante una **interfaz gráfica** y comprobar el funcionamiento de múltiples hilos trabajando sobre un **recurso compartido**.

La utilización de `synchronized` en la zona de carga evita interferencias entre los repartidores y garantiza que un pedido retirado por un repartidor no pueda ser retirado nuevamente por otro.

La interfaz gráfica permite:

* Registrar pedidos.
* Visualizar pedidos.
* Actualizar información.
* Iniciar las entregas desde una ventana principal.

Al finalizar la simulación, los pedidos procesados terminan en estado:

```text
ENTREGADO
```

---

# 👤 Autor

Proyecto desarrollado como actividad académica de **Programación Orientada a Objetos, Programación Concurrente e Interfaces Gráficas en Java**.
