# Projeto PROO 2019

## 1. Objetivos

Este projeto tem como objetivo servir como instrumento avaliador para o 4º bimestre na disciplina de Programação Orientada a Objetos.


O sistema consiste de uma biblioteca de programação (API) voltada para o gerenciamento de blogs. Nesta pode-se:
   * Realizar operações de CRUD nos perfis de usuários, blogs, posts e comentários.

Para o desenvolvimento da referida biblioteca, foi disponibilizada uma classe denominada Fachada que contem as funcionalidades que a biblioteca deve atender tais como métodos de criação de perfil de usuário, edição, criação de blogs, criação de postagens etc. A utilização dessa classe fachada tem por objetivo criar uma camada de abstração entre a lógica de negócio implementada no interior da biblioteca e os possíveis clientes de tal biblioteca seguindo os preceitos do padrão de projeto Fachada (ver maiores detalhes em https://www.devmedia.com.br/padrao-de-projeto-facade-em-java/26476). Neste sentido, é importante prestar atenção aos seguintes aspectos:

   1. Por se tratar de uma biblioteca cujo acesso se dá a partir dos métodos da fachada que especificam a lista de parâmetros passados pelo cliente, não há necessidade de interação alguma com o usuário. Isto é, não é necessário em nenhum ponto da implementação a leitura de dados do teclado ou a escrita de dados na tela);
   2. Conforme discutido no artigo acima, a utilização de uma fachada indica a criação de uma camada de abstração para a bilbioteca. Em outras palavras, a biblioteca deve conter também as classes de domínio e DAO que dão sustentação à biblioteca.


### 1.1.  Sobre as classes de testes

A fim de guiar o processo de desenvolvimento e avaliação do projeto foi construída uma bateria de testes utilizando o framework JUnit. Tal bateria de testes é composta de 11 unidades de testes representando cada os testes aplicados em um subconjunto das funcionalides do projeto. 

### 1.2. Sobre a persistência
A biblioteca construída deve realizar persistência dos dados submetidos em banco de dados. Em relação às propriedades de acesso ao banco (url, username e password) as mesmas devem ser definidas no arquivo de configuração application.properties localizado no diretório resources. Para carregamento, deve-se fazer uso da classe Properties (ver maiores detalhes em https://www.devmedia.com.br/utilizando-arquivos-de-propriedades-no-java/25546).

Um outro elemento que deve ser considerado são as classes DAO que possibilitarão melhor organização do código.



## 2. Entregáveis
Para o desenvolvimento a equipe deverá seguir os seguintes passos:
   1. Baixar o projeto disponibilizado pelo professor em (https://github.com/ivocalado/projeto-proo-2019).
   2. Realizar a devida implementação do projeto e submeter a versão do projeto via SIGAA.

## 2. Forma de avaliação

* 60% desempenho nos testes de aceitação
* 20% modelagem das classes Java
* 20% salvamento dos dados no banco de dados via JDBC

## 3. Data de entrega

A entrega da atividade será dividida em duas etapas com igual objetivo sendo que a segunda etapa consistirá da reapresentação do trabalho visando avaliar as correções aplicadas pelos grupos. Caso algum grupo atinja a nota 10,0 na primeira apresentação não será necessário a reapresentação. Seguem as datas:

* 1ª entrega: 21/11/2019 até às 23:59
* 2ª entrega: 29/11/2019 até às 12:00 (meio-dia), impreterivelmente.

# IMPORTANTE: Penalidade por cópia

Caso seja detectado cópia entre trabalhos, ainda que em trecho mínimo, todo o trabalho será desconsiderado para ambas as equipes.
