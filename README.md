## Problema 1: Clase `Rectangulo`

### Métodos
1. **`area`**: Calcula y retorna el área del rectángulo.
   - Parámetros:
     - `base` (entero o double).
     - `altura` (entero o double).
   - Fórmula: Área = base × altura

2. **`perimetro`**: Calcula y retorna el perímetro del rectángulo.
   - Parámetros:
     - `lado1` (entero o double).
     - `lado2` (entero o double).
   - Fórmula: Perímetro = 2 × (lado1 + lado2)

## Problema 2: Clase `Empleado`

### Métodos
1. **`calcularSalario`**: Calcula y retorna el salario del empleado.
   - Parámetros:
     - `sueldoBase` (entero o double): Sueldo base del empleado.
     - `bonificacion` (entero o double, opcional): Bonificación adicional (si se proporciona).
     - `horasExtras` (entero o double, opcional): Horas extras trabajadas (si se proporciona).
   - Instrucciones:
     - Si solo se proporciona el sueldo base, retornar solo eso.
     - Si se proporciona sueldo base y bonificación, retornar sueldo base + bonificación.
     - Si se proporciona sueldo base, bonificación y horas extras, retornar sueldo base + bonificación + (horasExtras × 20).

## Problema 3: Clase `CalculadoraImpuestos`

### Método
1. **`calcularImpuestos`**: Calcula y retorna los impuestos a pagar.
   - Parámetros:
     - `ingresos` (entero): Ingresos totales.
     - `porcentajeImpuestos` (double): Porcentaje de impuestos a aplicar.
     - `dividendos` (double, opcional): Ganancias por dividendos (si se proporciona).
     - `exencion` (double, opcional): Exención de impuestos (si se proporciona).
   - Instrucciones:
     - Si solo se proporcionan los ingresos, retornar `ingresos + 0.15`.
     - Si se proporcionan dividendos y porcentaje de impuestos, retornar `dividendos × (porcentajeImpuestos / 100)`.
     - Si se proporcionan dividendos, porcentaje de impuestos y exención:
       - Calcular los impuestos: `impuestos = dividendos × (porcentajeImpuestos / 100)`.
       - Si los impuestos son mayores que la exención, retornar `impuestos - exencion`.
       - Si no, retornar 0.
