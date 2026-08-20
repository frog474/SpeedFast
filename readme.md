# SpeedFast


Proyecto desarrollado en Java para representar el sistema de pedidos de la empresa de reparto SpeedFast.


En esta segunda semana, el proyecto aplica conceptos de Programación Orientada a Objetos, principalmente **clases abstractas, herencia, sobrescritura y polimorfismo**.


## Descripción del proyecto


SpeedFast ofrece tres tipos de pedidos:


- **Pedido de Comida:** calcula el tiempo de entrega considerando 15 minutos base más 2 minutos por cada kilómetro.
- **Pedido de Encomienda:** calcula el tiempo de entrega considerando 20 minutos base más 1,5 minutos por cada kilómetro.
- **Pedido Express:** considera 10 minutos base y agrega 5 minutos si la distancia supera los 5 kilómetros.


Para representar estos tipos de pedidos se creó una clase abstracta llamada `Pedido` y tres clases derivadas:


- `PedidoComida`
- `PedidoEncomienda`
- `PedidoExpress`


La clase `Pedido` contiene los atributos comunes `idPedido`, `direccionEntrega` y `distanciaKm`.


Además, posee el método `mostrarResumen()`, que muestra la información básica del pedido, y el método abstracto `calcularTiempoEntrega()`, que es implementado de forma diferente por cada clase derivada.


## Cálculo del tiempo de entrega


### PedidoComida


```text
15 minutos + (2 × distancia en km)
PedidoEncomienda
20 minutos + (1,5 × distancia en km)

El resultado se ajusta a un número entero.

PedidoExpress
10 minutos base
+ 5 minutos si la distancia es mayor a 5 km
Tecnologías utilizadas
Java
IntelliJ IDEA
JDK 25
GitHub
Estructura del proyecto
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