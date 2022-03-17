# :man_technologist: Curso Web Full Stack - Let's Code :man_student:

## Projeto Módulo 08 - Desenvolvimento Web :rocket:

Neste módulo podemos consolidar nossos conhecimento quanto ao funcionamento do framework Spring, assim como a construção de métodos HTTP e padrões de arquietetura REST no desenvolvimento de uma API que é contextualizada no "mundo" Star Wars.

___________________________________________________________________________________________________________________________________________________________________

### :scroll: Enredo que a atividade propôs
#### Descrição do problema
O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes.
Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar recursos entre os rebeldes.

#### Requisitos funcionais
Você irá desenvolver uma API REST (sim, nós levamos a arquitetura da aplicação a sério mesmo no meio de uma guerra), ao qual irá armazenar informação sobre os rebeldes, bem como os recursos que eles possuem.

___________________________________________________________________________________________________________________________________________________________________

### :chains: Tecnologias
- Spring/JAVA
- Banco de dados MySQL (utilizando JDBC com o Spring para realização das querys)
- Lombok
- Gradle

Obs.: Tendo em vista o custo envolvido para manutenção de um servidor na núvem, após dia 31/03/2022 o removeremos, porém todo o código de implementação continuará a disposição.

#### FrontEnd
O front foi feito em Angular e encontra-se disponível no seguinte repositório:</br>
`https://github.com/ffsilva27/StarWarsSocialNetworkAPI-Angular`

___________________________________________________________________________________________________________________________________________________________________

### :man_technologist: Implementações

#### Adicionar rebeldes
O método de criação de um rebelde tinha como requisitos mínimos que ele possuisse nome, idade, gênero e localização(nome da galáxia, latitude e longitude).
No alistamento do rebelde, esse deverá receber um inventário de itens(arma, munição, água e comida), não podendo escolher quais e/ou quantidade sendo gerado pela aplicação, onde a mudança so é possível mediante negociação com outros rebeldes.

##### Método HTTP para adicição do rebelde/JSON
 - (POST) `/rebeldes`
```json
{
	"username": "UserDoRebelde",
	"senha": "1234",
	"nome":"NomeDoRebelde",
	"avatar": "",
	"idade":10,
	"genero":"masculino",
	"localizacao":{
			"nomeDaGalaxia":"testeGaláxia"
	}
}
```
Obs.: Apesar da atividade não ter sido obrigatória a implementação do front, nós o fizemos, sendo assim a propriedade "avatar" é populada pelo front, não sendo obrigatória em testes no postman, por exemplo.

#### Atualizar localização do rebelde
O método tem como princípio que o rebelde possa informar sua localização atualizada, reportando assim a galáxia, latitude e longitude, sendo estes 2 últimos fictícios.

##### Método HTTP para atualização da localização do rebelde/JSON
 - (PATCH) `/rebeldes/atualizarlocalizacao/{id}`

```json
{
	"nomeDaGalaxia":"NomeDaGalaxia",
	"latitude": 10,
	"longitude": 10
}
```

#### Reportar rebelde como um traidor
Eventualmente algum rebelde poderar trair a "resistência", quando isso acontecer o rebelde poderá ser reportado como traidor. Após 3 reports, esse rebelde fica impedido de negociar os itens de seu inventário.

##### Método HTTP para atualização da localização do rebelde
 - (PATCH) `/rebeldes/traidor/{id}`

Esse método não se faz necessário informar um body, apenas a numeração do ID no path, conforme acima (sem as chaves).

#### Negociar itens
Os rebeldes podem trocar itens entre si, porém devem respeitar a tabela de pontos abaixo. Para a negociação ser concretizada, a soma dos itens multiplicada pelos pontos de cada item devem ser iguais para ambos.
Ex.: 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3).

#### Tabela de pontos

| ITEM | PONTOS |
|------|--------|
| 1 Arma | 4 |
| 1 Munição | 3 |
| 1 Água | 2 |
| 1 Comida | 1 |

##### Método HTTP para negociação de itens/JSON
 - (PATCH) `/rebeldes/negociar`

```json
{
	"idRemetente": "d61cef94-141c-4f96-9971-769c740a94b5",
	"idDestinatario": "eba050ac-b492-477b-b64a-a0910d1b06c9",
	"itemRemetente": ["agua"],
	"itemDestinatario": ["arma"],
	"qtdItemRemetente": [1],
	"qtdItemDestinatario": [1]
}
```

#### Relatórios
A API fornece alguns relatórios, como:
 - Porcentagem de traidores;
 - Porcentagem de rebeldes;
 - Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde);
 - Pontos perdidos devido a traidores.

##### Método HTTP para obtenção dos relatórios
 - (GET) `/rebeldes/report/traidores`
 - (GET) `/rebeldes/report/rebeldes`
 - (GET) `/rebeldes/report/recursos`
 - (GET) `/rebeldes/report/pontosperdidos`

Esse método não se faz necessário informar um body, apenas a numeração do ID no path, conforme acima (sem as chaves).

#### Login
Apesar de a atividade não prevê o desenvolvimento do front, nossa equipe resolveu desenvolver essa camada. Sendo assim tivemos que projetar controller e service para tratamento, possibilitando que fosse logado apenas rebeldes cadastrados.

##### Método HTTP para login do rebelde/JSON
 - (POST) `/rebeldes/login`

```json
{
  "username": "UserRebelde",
  "senha": "1234"
}
```

#### Deletar rebelde
Apesar de não tratar-se de um requisito obrigatório para o projeto, resolvemos implementar um Banco de Dados para ele, onde as informações são salvas em um servidor na AWS. Para facilitar o trabalho, resolvemos criar o método de exclusão dos rebeldes.

##### Método HTTP para exclusão
 - (DELETE) `/rebeldes/{ID}`

Esse método não se faz necessário informar um body, apenas a numeração do ID no path, conforme acima (sem as chaves).

#### Detalhes do rebelde
A API fornece um endpoint para obtenção de informações referentes a apenas um rebelde.

##### Método HTTP para obtenção de informações referente a um rebelde
 - (GET) `/rebeldes/{ID}`

Esse método não se faz necessário informar um body, apenas a numeração do ID no path, conforme acima (sem as chaves).

## :man_technologist: Desenvolvedores<br>
[Filipe Silva](https://github.com/ffsilva27) , 
[Felipe Garé](https://github.com/FelipeRodriguesGare) , 
[Samuel Bruing](https://github.com/sgbruing) , 
[Vitor Zillig](https://github.com/VitorZillig) .
