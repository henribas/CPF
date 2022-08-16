# CPF-CNPJ
Validação e geração de CPF e CNPJ com modelos sugeridos de classes e relacionamentos.

O Cadastro de Pessoa Física ou CPF é composto por 11 dígitos numéricos geralmente apresentados com uma máscara no formato: NNN.NNN.NNN-NN onde N representa os números do CPF. O foco aqui está na validação do CPF. Para mais informações sobre o CPF o endereço da Wikipedia pode ser consultado: https://pt.wikipedia.org/wiki/Cadastro_de_Pessoas_F%C3%ADsicas
Os dois últimos números do CPF são dígitos verificadores e no artigo da Wikipedia temos: "Os dígitos verificadores são calculados através de um algoritmo que soma o produto de cada dígito que compõe o CPF por um peso e calcula o resto da divisão dessa soma por 11. Pela característica, esse algoritmo é conhecido como "módulo 11"."
Um exemplo prático pode facilitar o entendimento. Partindo de um CPF fictício válido e de número 725.478.824-26 pode ser aplicada a regra acima para melhor entendimento.
Cada dígito do CPF é multiplicado por um fator. De 10 até 2, começando pela esquerda, como mostra a tabela abaixo.

| CPF sem dígito verificador | 7 |  2 |  5 |  4 |  7 |  8 |  8 | 2 | 4 |
| -------------------------- | - |  - |  - |  - |  - |  - |  - | - | - |
| Multiplicador              |10 |  9 |  8 |  7 |  6 |  5 |  4 | 3 | 2 |
| Resultado da multiplicação |70 | 18 | 40 | 28 | 42 | 40 | 32 | 6 | 8 |

O resultado da multiplicação é somado: 70 + 18 + 40 + 28 + 42 + 40 + 32 + 6 + 8 = 264.

A soma é dividida por 11 (mencionado no artigo, lembra?): 264 / 11 = 24 com resto zero.
