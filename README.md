# CPF
Validação e geração de CPF com modelos sugeridos de classes e relacionamentos.

O Cadastro de Pessoa Física ou CPF é composto por 11 dígitos numéricos geralmente apresentados com uma máscara no formato: NNN.NNN.NNN-NN onde N representa os números do CPF. O foco aqui está na validação do CPF. Para mais informações sobre o CPF o endereço da Wikipedia pode ser consultado: https://pt.wikipedia.org/wiki/Cadastro_de_Pessoas_F%C3%ADsicas
Os dois últimos números do CPF são dígitos verificadores e no artigo da Wikipedia temos: "Os dígitos verificadores são calculados através de um algoritmo que soma o produto de cada dígito que compõe o CPF por um peso e calcula o resto da divisão dessa soma por 11. Pela característica, esse algoritmo é conhecido como "módulo 11"."
Um exemplo prático pode facilitar o entendimento. Partindo de um CPF fictício válido e de número 725.478.824-26 pode ser aplicada a regra acima para melhor entendimento.
Cada dígito do CPF é multiplicado por um fator. De 10 até 2, começando pela esquerda, como mostra a tabela abaixo.

| CPF sem dígito verificador | 7 |  2 |  5 |  4 |  7 |  8 |  8 | 2 | 4 |
| -------------------------- | - |  - |  - |  - |  - |  - |  - | - | - |
| Multiplicador              |10 |  9 |  8 |  7 |  6 |  5 |  4 | 3 | 2 |
| Resultado da multiplicação |70 | 18 | 40 | 28 | 42 | 40 | 32 | 6 | 8 |

O resultado da multiplicação é somado: 70 + 18 + 40 + 28 + 42 + 40 + 32 + 6 + 8 = 284.

A soma é dividida por 11 (mencionado no artigo, lembra?): 264 / 11 = 25 com o resto 9 (prova: 25 x 11 = 275 e 275 + 9 = 284).

Subtraindo o resto de 11 temos: 11 - 9 = 2. Este é o primeiro digito verificador.

É necessário repetir o processo acima mas desta vez incluindo o dígito verificador encontrado. Portanto, cada dígito do CPF é multiplicado por um fator. De 11 até 2, começando pela esquerda, como mostra a tabela abaixo.

| CPF com 1 dígito verificador | 7 |  2 |  5 |  4 |  7 |  8 |  8 | 2 |  4 | 2 |
| ---------------------------- | - |  - |  - |  - |  - |  - |  - | - |  - | - |
| Multiplicador                |11 | 10 |  9 |  8 |  7 |  6 |  5 | 4 |  3 | 2 |
| Resultado da multiplicação   |77 | 20 | 45 | 32 | 49 | 48 | 40 | 8 | 12 | 4 |

O resultado da multiplicação é somado: 77 + 20 + 45 + 32 + 49 + 48 + 40 + 8 + 12 + 4 = 335.

A soma é dividida por 11: 335 / 11 = 30 com o resto 5 (prova: 30 x 11 = 330 e 330 + 5 = 335).

Subtraindo o resto de 11 temos: 11 - 5 = 6. Este é o segundo digito verificador.
