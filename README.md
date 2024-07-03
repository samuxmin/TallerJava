Sistema de Peaje

Este proyecto es un sistema de peaje diseñado con Jakarta EE, que implementa una API RESTful para interactuar con otros sistemas de pago externos. Utiliza una arquitectura moderna y una variedad de tecnologías para garantizar un rendimiento óptimo y una gestión eficiente.
Tecnologías Utilizadas

    Backend: Jakarta EE
    APIs Externas:
        SOAP: Para la integración con un sistema de pago externo.
        REST: Para la integración con otro sistema de pago externo.
    Base de Datos: MySQL
    Cola Asíncrona: Para la gestión de pagos.
    Pruebas: JUnit
    Monitoreo: Grafana, InfluxDB y Docker
    Simulación de Endpoints: JMeter

Descripción

El sistema de peaje permite la gestión eficiente de transacciones de pago a través de una API RESTful. Esta API interactúa con sistemas de pago externos que utilizan diferentes tecnologías (SOAP y REST), asegurando una integración robusta y flexible.
Backend

El backend, construido con Jakarta EE, maneja toda la lógica del negocio y la comunicación con los sistemas de pago externos. Implementa una cola asíncrona para gestionar los pagos de manera eficiente, asegurando que las transacciones se procesen sin problemas.
Base de Datos

Utilizamos MySQL para almacenar información crítica relacionada con las transacciones y los usuarios. MySQL ofrece una solución robusta y escalable para el manejo de datos.
Monitoreo y Simulación

    Monitoreo: Implementado con Grafana, InfluxDB y Docker para asegurar la disponibilidad y el rendimiento del sistema.
    Simulación de Endpoints: Utilizamos JMeter para simular las llamadas a los endpoints, permitiendo pruebas exhaustivas y asegurando la fiabilidad del sistema.

Características Principales

    Integración con Sistemas de Pago Externos: Compatible con APIs SOAP y REST.
    Gestión Asíncrona de Pagos: Utiliza una cola asíncrona para procesar transacciones de manera eficiente.
    Pruebas Automatizadas: Implementadas con JUnit para garantizar la calidad del código.
    Monitoreo Completo: Solución de monitoreo con Grafana, InfluxDB y Docker.
    Simulación de Cargas: Realizada con JMeter para probar la resistencia y el rendimiento del sistema.
