La aplicación consta de 6 módulos y se centra en la pasada de vehiculos por peaje

Comienza en el modulo peaje cuando pasa un vehiculo
este verifica el tag y de poseer, se comunica con el modulo gestion de usuarios para intentar realizar PREpago o PostPago.
De ser postpago este debe comunicarse con el módulo medios de pago.
Si es nacional y no posee tag o no fueron posibles estos medios de pago,
se comunica con el modulo sucive para registrar el pago por matricula

Los casos de uso principales están representados en el archivo CasosDeUsoTest
