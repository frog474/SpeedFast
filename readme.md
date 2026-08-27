# SpeedFast

## Descripción

SpeedFast es un sistema de gestión de pedidos de reparto desarrollado en Java. El proyecto permite administrar distintos tipos de pedidos: comida, encomiendas y pedidos express, cada uno con su propia lógica para la asignación de repartidores y el cálculo del tiempo estimado de entrega.

El sistema fue desarrollado aplicando conceptos de Programación Orientada a Objetos, principalmente **abstracción, herencia, polimorfismo, sobrecarga, sobrescritura e interfaces**.

## Objetivo

Implementar un sistema orientado a objetos que permita gestionar pedidos de distintos tipos y sus operaciones de envío, demostrando el uso de una clase abstracta, clases derivadas e interfaces.

El sistema permite:

* Asignar repartidores automáticamente.
* Asignar un repartidor manualmente.
* Mostrar el resumen de cada pedido.
* Calcular el tiempo estimado de entrega.
* Despachar pedidos.
* Cancelar pedidos.
* Registrar y visualizar el historial de entregas.

## Tecnologías utilizadas

* Java
* IntelliJ IDEA
* Programación Orientada a Objetos
* ArrayList
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
    │   └── PedidoExpress.java
    │
    └── service/
        └── ControladorDeEnvios.java
```

## Clases

### Pedido

`Pedido` es una clase abstracta que contiene los atributos y comportamientos comunes de los diferentes tipos de pedidos.

Sus principales atributos son:

* `idPedido`
* `direccionEntrega`
* `distanciaKm`
* `repartidor`

La clase contiene el método `mostrarResumen()`, que permite mostrar la información principal del pedido.

También posee dos versiones del método `asignarRepartidor()`:

```java
asignarRepartidor()
asignarRepartidor(String nombreRepartidor)
```

Esto permite demostrar **sobrecarga de métodos**, ya que el segundo método recibe el nombre del repartidor como parámetro.

Finalmente, `Pedido` define el método abstracto:

```java
calcularTiempoEntrega()
```

Cada clase derivada implementa este método de acuerdo con sus propias reglas.

### PedidoComida

Representa un pedido de comida.

La asignación automática establece a **Luis Díaz** como repartidor.

El tiempo de entrega se calcula mediante:

```text
15 + (2 × distancia en km)
```

### PedidoEncomienda

Representa un pedido de encomienda.

La asignación automática establece a **Daniela Tapia** como repartidora.

El tiempo de entrega se calcula mediante:

```text
20 + (1.5 × distancia en km)
```

Además, en `Main` se demuestra la asignación manual utilizando la sobrecarga de `asignarRepartidor(String nombreRepartidor)`.

### PedidoExpress

Representa un pedido express.

La asignación automática establece a **Pedro Soto** como repartidor.

Su tiempo de entrega depende de la distancia:

* Si la distancia es mayor a 5 km: **15 minutos**.
* Si la distancia es menor o igual a 5 km: **10 minutos**.

## Interfaces

El proyecto utiliza tres interfaces para separar responsabilidades específicas.

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

Permite cancelar el pedido seleccionado.

### Rastreable

Define el método:

```java
void verHistorial();
```

Permite visualizar el historial de entregas realizadas.

## ControladorDeEnvios

`ControladorDeEnvios` implementa las tres interfaces:

* `Despachable`
* `Cancelable`
* `Rastreable`

Esta clase se encarga de controlar las operaciones de envío.

Utiliza un `ArrayList<String>` para almacenar el historial de pedidos despachados.

También posee el método `seleccionarPedido(Pedido pedido)`, que permite establecer el pedido sobre el cual se realizará una operación.

## Conceptos de Programación Orientada a Objetos

### Abstracción

La clase `Pedido` está definida como una clase abstracta. Contiene los elementos comunes de todos los pedidos y establece el método abstracto `calcularTiempoEntrega()`.

Esto permite que cada tipo de pedido implemente su propio cálculo.

### Herencia

Las clases:

* `PedidoComida`
* `PedidoEncomienda`
* `PedidoExpress`

heredan de `Pedido`.

De esta forma, reutilizan sus atributos y métodos comunes.

### Sobrescritura

Cada clase derivada sobrescribe los métodos:

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

El primero permite una asignación automática mediante las clases derivadas, mientras que el segundo permite indicar manualmente el nombre del repartidor.

### Polimorfismo

El polimorfismo se demuestra en `Main` mediante un arreglo de tipo `Pedido` que contiene objetos de las tres clases derivadas:

```java
Pedido[] pedidos = {
    pedidoComida,
    pedidoEncomienda,
    pedidoExpress
};
```

Al recorrer este arreglo y llamar a `calcularTiempoEntrega()`, cada objeto ejecuta la implementación correspondiente a su propia clase.

### Interfaces

Las interfaces permiten separar las responsabilidades relacionadas con el despacho, cancelación y consulta del historial.

`ControladorDeEnvios` implementa las tres interfaces, permitiendo mantener estas funciones organizadas y desacopladas de la jerarquía de pedidos.

## Simulación

La clase `Main` realiza una simulación completa del sistema.

Durante la ejecución se realizan las siguientes operaciones:

1. Creación de un pedido de comida.
2. Creación de una encomienda.
3. Creación de un pedido express.
4. Asignación automática de un repartidor.
5. Asignación manual de un repartidor mediante sobrecarga.
6. Visualización del resumen de los pedidos.
7. Cálculo de los tiempos estimados de entrega.
8. Demostración de polimorfismo.
9. Despacho de los pedidos de comida y encomienda.
10. Cancelación del pedido express.
11. Visualización del historial de entregas.

## Ejemplo de ejecución

```text
===== SPEEDFAST =====

[Pedido Comida]
Repartidor asignado automáticamente: Luis Díaz
Pedido #101
Dirección: Av. Italia 456
Distancia: 4.0 km
Tiempo estimado: 23 minutos

[Pedido Encomienda]
Repartidor asignado: Daniela Tapia
Pedido #102
Dirección: Av. Independencia 123
Distancia: 6.0 km
Tiempo estimado: 29 minutos

[Pedido Express]
Repartidor asignado automáticamente: Pedro Soto
Pedido #103
Dirección: Av. Apoquindo 1500
Distancia: 7.0 km
Tiempo estimado: 15 minutos

===== POLIMORFISMO =====

Pedido #101
Tiempo estimado: 23 minutos

Pedido #102
Tiempo estimado: 29 minutos

Pedido #103
Tiempo estimado: 15 minutos

===== DESPACHO =====

Pedido #101 despachado correctamente.
Pedido #102 despachado correctamente.

Cancelando Pedido Express #103...
→ Pedido #103 cancelado exitosamente.

===== HISTORIAL =====

Historial:
- Pedido #101 – entregado por Luis Díaz
- Pedido #102 – entregado por Daniela Tapia
```

## Escalabilidad y mantenibilidad

La estructura del proyecto facilita la incorporación de nuevos tipos de pedidos. Una nueva clase puede heredar de `Pedido` e implementar sus propias reglas para la asignación del repartidor y el cálculo del tiempo de entrega.

La utilización de interfaces permite mantener separadas las responsabilidades de despacho, cancelación y seguimiento.

La abstracción y la herencia permiten reutilizar código común, mientras que el polimorfismo permite trabajar con diferentes tipos de pedidos mediante una referencia de la clase base.

Esto contribuye a que el sistema sea más **organizado, reutilizable, mantenible y escalable**.

## Autor

Proyecto desarrollado como actividad académica de Programación Orientada a Objetos.
