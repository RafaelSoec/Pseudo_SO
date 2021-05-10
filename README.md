# Pseudo_SO

## Universidade de Brasília

### Disciplina de Sistemas Operacionais

#### GRUPO 4
#### Rafael Souza - 15/0081537
#### Caio Calixto Fasolak Alves - 15/0078676
#### Jean Rodrigues Magalhães - 15/0079923
#### Marcelo Áxel C. de Nazaré - 15/0080727}


Implementação de um pseudo-SO multiprogramado, composto por um Gerenciador de Processos,
por um Gerenciador de Memória e por um Gerenciador de Entrada/Saída. O gerenciador de processos
deve ser capaz de aplicar o algoritmo de escalonamento definido por meio de parâmetro pelo usuário do SO.
O gerenciador de memória deve garantir que um processo não acesse as regiões de memória de um outro
processo, e que o algoritmo de substituição de página seja adequadamente usado. E o gerenciador de
entrada/saída deve ser responsável por administrar o algoritmo especificado para a busca em disco. Cada
módulo será testado de acordo com as especificações determinadas abaixo. Além disso, o pseudo-SO deve
receber como parâmetro um inteiro e um arquivo texto, por exemplo $ 1 processes.txt. O inteiro determina
qual módulo deve ser ativado (no exemplo dado significa que será ativado o módulo de processos, pois foi o
inteiro 1), e o arquivo texto (com extensão .txt) repassa os dados de entrada necessários para a execução do
módulo escolhido.
