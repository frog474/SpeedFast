# SpeedFast

Proyecto desarrollado en Java para representar el sistema de pedidos de la empresa de reparto SpeedFast.

El proyecto aplica conceptos de Programación Orientada a Objetos, principalmente **herencia, sobrecarga, sobreescritura y polimorfismo**.

## Descripción del proyecto

SpeedFast ofrece tres tipos de servicios de reparto:

- **Pedido de Comida:** requiere un repartidor con mochila térmica.
- **Pedido de Encomienda:** requiere validar el peso y el embalaje.
- **Pedido Express:** requiere un repartidor disponible inmediatamente y considera su distancia.

Para representar estos servicios se creó una clase base llamada `Pedido` y tres clases derivadas:

- `PedidoComida`
- `PedidoEncomienda`
- `PedidoExpress`

Cada tipo de pedido implementa su propia lógica para la asignación de repartidores.

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- JDK 25
- GitHub

## Estructura del proyecto

```text
SpeedFast
│
├── src
│   ├── model
│   │   ├── Pedido.java
│   │   ├── PedidoComida.java
│   │   ├── PedidoEncomienda.java
│   │   └── PedidoExpress.java
│   │
│   └── app
│       └── Main.java
│
└── README.md