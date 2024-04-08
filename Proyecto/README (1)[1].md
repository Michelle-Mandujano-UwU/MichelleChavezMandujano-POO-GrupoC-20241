# Zoológico de Morelia

El zoológico de Morelia requiere un sistema para poder administrar todas sus operaciones, así como poder llevar la gestión de todo su personal / visitante / animales.

El director del zoológico require que el sistema pueda contar primeramente con una opción para poder registrar a todos sus empleados, con datos como:

`Nombre` `Fecha de nacimiento` `RFC` `Salario`
`Rol (veterinario, guía, mantenimiento y administración`
`Apellidos` `Fecha de ingreso como tranajador` `CURP` `Horario`

De igual forma, le gustaría poder almacenar toda la información de sus visitantes, y de esta forma, contar con promociones para los visitantes más frecuentes. De los visitantes se require la siguiente información.

`Nombre` `Fecha de nacimiento` `CURP` `Apellidos` `Numero de visitas` `Fecha de registro`

Al director del zoológico le gustaría contar con una opción para poder registrar cada vez que haya una visita en el zoológico (el precio del boleto por persona es de $100 MXN por adulto y $50 MXN por niño). Por lo tanto, cada que un visitante llegue al zoológico, desea que en el sistema pueda registrar esa visita y que en esa ella se guarde el guía que estará a cargo de ella, así como los visitantes que formarán parte de ella. Es decir, una visita es guiada por un guía y puede tener uno o muchos visitantes en ella. Es necesario recordar que al momento de que se registra una visita, el atributo de número de visitas del cliente debe de aumentar en 1, ya que cada 5 visitas, el visitante tendrá un descuento del 20% en su boleto. Resumiendo lo anterior, el director desea que al momento de registrar una visita se registre lo siguiente.

`Guia a cargo` `Costo total de la visita` `Cantidad de niños` `Cantidad de adultos`
`Fecha de visita` `Visitantes`.

Para este sistema también se requiere que se pueda ir registrando cada animal nuevo que ingrese al zoológico, con todos sus datos.  Tales como: 

'Tipo de animal' 'Fecha nacimiento' 'Fecha de llegada al zoológico' 'Peso' 'Frecuencia de alimentación' 'Enfermedades' 'Tipo de alimentación' 'Tiene vacunas'.

El programa también busca gestionar las actividades diarias dentro del zoológico. Esto incluye no solo el registro de los animales y empleado, sino también supervisar y llevar un registro de las rutinas de alimentación de los animales y cualquier tarea de mantenimiento realizada en las instalaciones, ya que esto nos permiste llevar un seguimiento de todas las operaciones llevadas a cabo en el lugar. Para esto se requerirán los datos siguientes: 

'Empleado que encargado (debe ser un empleado con el rol de mantenimiento)' 'ID del animal' 'Proceso que se realizó (Mantenimiento, limpieza, alimentación, etc)' 'Fecha del proceso' 'Observaciones (opcional)'

El director también quiere que sólo él pueda tener opción de realizar varias cosas para los empleados, los animales y los visitantes del zoológico.

Primero, quiere que el programa pueda registrar información, como nombres y detalles importantes. También quiere que se pueda cambiar la información, pero solo si no afecta otras cosas, como las visitas o los trabajos de mantenimiento. Además, el programa debe permitir eliminar información, pero nuevamente, solo si no causa problemas con otras cosas.

Además, el programa debe poder buscar información cuando sea necesario.

Para las visitas y los trabajos de mantenimiento, el director del zoológico solo quiere que el programa pueda registrar información y buscarla después. No quiere que se pueda borrar ni cambiar una vez que se haya registrado.

Por último, el jefe quiere que el programa tenga una contraseña. Solo él debe poder acceder al programa y hacer todas las cosas que mencionamos antes.


**Integrantes de equipo**

-Chávez Mandujano Abril Michelle

-Campos Rosas Diana Laura

-Arguello Escutia Luis Antonio

-Meneses Diaz Mariana Sofia




