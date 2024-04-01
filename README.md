# Construindo API REST com Spring Boot

Spring e Spring Boot são estruturas Java usadas para construir aplicativos, que ajudam os desenvolvedores a construir e estruturar seu código de maneira eficiente e escalonável.

À medida que construímos a API Family Cash Card, usaremos Spring MVC para a aplicação web, Spring Data para acesso a dados e Spring Security para autenticação e autorização.

Spring Boot é como uma versão mais opinativa do Spring; vem com muitas configurações e dependências pré-configuradas que são comumente usadas em aplicativos Spring tornando muito fácil começar rapidamente, sem ter que se preocupar em configurar tudo do zero. Além disso, o Spring Boot vem com um servidor web integrado, para que você possa criar e implantar facilmente aplicativos web sem precisar de um servidor externo.

Spring Boot permite configurar como e quando as dependências são fornecidas ao seu aplicativo em tempo de execução. Isso coloca você no controle de como seu aplicativo opera em diferentes cenários.

## Inicialização Spring
Ao iniciar um novo aplicativo Spring Boot, Spring Initializr é a primeira etapa recomendada. Você pode pensar no Spring Initializr como um carrinho de compras para todas as dependências que seu aplicativo possa precisar. Ele irá gerar de forma rápida e fácil um aplicativo Spring Boot completo e pronto para execução.

## Contratos de API
A indústria de software adotou vários padrões para capturar o comportamento acordado da API na documentação e no código. Esses acordos são frequentemente chamados de “contratos”. Dois exemplos incluem Contratos Orientados ao Consumidor e Contratos Orientados ao Fornecedor.

Definimos um contrato de API como um acordo formal entre um fornecedor de software e um consumidor que comunica abstratamente como interagir entre si. Este contrato define como os provedores de API e os consumidores interagem, como são as trocas de dados e como comunicar casos de sucesso e falha.

O provedor e os consumidores não precisam compartilhar a mesma linguagem de programação, apenas os mesmos contratos de API. Para o domínio Family Cash Card, vamos supor que atualmente exista um contrato entre o serviço Cash Card e todos os serviços que o utilizam. Abaixo está um exemplo desse primeiro contrato de API.

![Captura de tela 2024-02-20 121941](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/5d626c23-0bc0-41ca-938a-eec30e165b03)


## Por que os contratos de API são importantes?
Os contratos de API são importantes porque comunicam o comportamento de uma API REST. Eles fornecem detalhes específicos sobre os dados que estão sendo serializados (ou desserializados) para cada comando e parâmetro sendo trocados. Os contratos de API são escritos de forma que possam ser facilmente traduzidos em funcionalidade de provedor e consumidor de API e testes automatizados correspondentes.

## O que é JSON?
JSON (Javascript Object Notation) fornece um formato de intercâmbio de dados que representa as informações específicas de um objeto em um formato que você pode ler e entender facilmente. Usaremos JSON como formato de intercâmbio de dados para a API Family Cash Card.

Quando comparado ao XML, o JSON lê e grava mais rápido, é mais fácil de usar e ocupa menos espaço. Você pode usar JSON com a maioria das linguagens de programação modernas e em todas as principais plataformas. Também funciona perfeitamente com aplicativos baseados em Javascript.

Por essas razões, o JSON substituiu amplamente o XML como o formato mais amplamente usado para APIs usadas por aplicativos Web, incluindo APIs REST.

## O que é desenvolvimento orientado a testes?
É comum que as equipes de desenvolvimento de software criem conjuntos de testes automatizados para se protegerem contra regressões. Frequentemente, esses testes são escritos após a criação do código do recurso do aplicativo. Adotaremos uma abordagem alternativa: escreveremos testes antes de implementar o código da aplicação. Isso é chamado de desenvolvimento orientado a testes (TDD).

Freqüentemente, esses testes são escritos após a criação do código do recurso do aplicativo. Adotaremos uma abordagem alternativa: escreveremos testes antes de implementar o código da aplicação. Isso é chamado de desenvolvimento orientado a testes (TDD).


## A Pirâmide de Teste
Freqüentemente, esses testes são escritos após a criação do código do recurso do aplicativo. Adotaremos uma abordagem alternativa: escreveremos testes antes de implementar o código da aplicação. Isso é chamado de desenvolvimento orientado a testes (TDD).

![piramidetestes](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/90360f7f-49dd-4204-b150-a64e5b1e7dd2)

Testes de Unidade: Um Teste de Unidade exercita uma pequena “unidade” do sistema que está isolada do resto do sistema. Eles devem ser simples e rápidos. Você deseja uma alta proporção de testes de unidade em sua pirâmide de testes, pois eles são essenciais para projetar software altamente coeso e pouco acoplado.

Testes de Integração: Os Testes de Integração exercitam um subconjunto do sistema e podem exercitar grupos de unidades em um teste. Eles são mais complicados de escrever e manter e são executados mais lentamente que os testes unitários.

Testes ponta a ponta: um teste ponta a ponta exercita o sistema usando a mesma interface que um usuário usaria, como um navegador da web. Embora extremamente completos, os testes ponta a ponta podem ser muito lentos e frágeis porque usam interações simuladas do usuário em interfaces de usuário potencialmente complicadas. Implemente o menor número desses testes.


## O loop de refatoração vermelho, verde
As equipes de desenvolvimento de software adoram agir rapidamente. Então, como você vai rápido para sempre? Melhorando e simplificando continuamente sua refatoração de código. Uma das únicas maneiras de refatorar com segurança é quando você tem um conjunto de testes confiável. Assim, o melhor momento para refatorar o código que você está focando atualmente é durante o ciclo TDD. Isso é chamado de ciclo de desenvolvimento Vermelho, Verde e Refatorar:

Vermelho: Escreva um teste com falha para a funcionalidade desejada.
Verde: Implemente a coisa mais simples que pode funcionar para fazer o teste passar.
Refatorar: Procure oportunidades para simplificar, reduzir a duplicação ou melhorar o código de outra forma sem alterar qualquer comportamento – refatorar .
Repita!

### Escrevendo um teste com falha
Aqui abordaremos uma breve introdução à biblioteca de testes JUnit e à ferramenta de construção Gradle. Também usaremos a abordagem Test-First para construir software.

As classes de teste em um projeto Java padrão estão no diretório src/test, não em src/main. No nosso caso, decidimos colocar nosso código no pacote example.cashcard, então nossos arquivos de teste devem estar no diretório src/test/java/example/cashcard.

Uma convenção comum (mas não um requisito) é sempre usar o sufixo Test para classes de teste. Nós fizemos isso aqui. O nome completo da classe CashCardJsonTest dá uma pista sobre a natureza do teste que estamos prestes a escrever.

## Implementando GET
>>REST, CRUD E HTTP

Vamos começar com uma definição concisa de REST : Representational State Transfer. Em um sistema RESTful, os objetos de dados são chamados de Representações de Recursos. O objetivo de uma API RESTful (Application Programming Interface) é gerenciar o estado desses recursos.
Dito de outra forma, você pode pensar em “estado” como “valor” e “Representação de Recursos” como um “objeto” ou “coisa”. Portanto, REST é apenas uma forma de gerenciar os valores das coisas. Essas coisas podem ser acessadas por meio de uma API e geralmente são armazenadas em um armazenamento de dados persistente, como um banco de dados.


![rest](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/3bd4ba6a-54a3-4119-9fe1-a17091fbf23d)

Um conceito frequentemente mencionado quando se fala em REST é CRUD . CRUD significa “Criar, Ler, Atualizar e Excluir”. Estas são as quatro operações básicas que podem ser executadas em objetos em um armazenamento de dados.

Outro conceito comum associado ao REST é o Protocolo de Transferência de Hipertexto. Em HTTP , um chamador envia uma solicitação para um URI. Um servidor web recebe a solicitação e a encaminha para um manipulador de solicitações. O manipulador cria uma resposta, que é então enviada de volta ao chamador.

Os componentes da Solicitação e Resposta são:

Solicitar
*Método (também chamado de Verbo)
*URI (também chamado de Endpoint)
*Corpo

Resposta
*Código de status
*Corpo

O poder do REST está na maneira como ele faz referência a um recurso e na aparência da solicitação e da resposta para cada operação CRUD.

Para C REATE: use o método HTTP POST.
Para R EAD: use o método HTTP GET.
Para U PDATE: use o método HTTP PUT.
Para D ELETE: use o método HTTP DELETE.

O URI do terminal para objetos Cash Card começa com a /cashcardspalavra-chave. READ, UPDATEe DELETEas operações exigem que forneçamos o identificador exclusivo do recurso de destino. O aplicativo precisa desse identificador exclusivo para executar a ação correta exatamente no recurso correto. Por exemplo, para READ, UPDATE, ou DELETEum Cash Card com o identificador "42", o ponto final seria /cashcards/42.


## O Corpo da Solicitação
Ao seguir as convenções REST para criar ou atualizar um recurso, precisamos enviar dados para a API. Isso geralmente é chamado de corpo da solicitação . As operações CREATEe UPDATE exigem que um corpo de solicitação contenha os dados necessários para criar ou atualizar adequadamente o recurso. Por exemplo, um novo Cash Card pode ter um valor inicial em dinheiro e uma UPDATEoperação pode alterar esse valor.

Anotações Spring e verificação de componentes
Uma das principais coisas que o Spring faz é configurar e instanciar objetos. Esses objetos são chamados Spring Beans e geralmente são criados pelo Spring (em vez de usar a newpalavra-chave Java). Você pode direcionar o Spring para criar Beans de várias maneiras.

Controladores Web Spring
No Spring Web, as solicitações são tratadas por controladores.

@RestController
class CashCardController {
}

Isso é tudo o que é preciso para dizer ao Spring: “crie um controlador REST”. O Controlador é injetado no Spring Web, que roteia as solicitações de API (tratadas pelo Controlador) para o método correto.

![controller](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/47fe6ec7-92b2-426f-b1a1-3ac19574dd7d)

Um método Controller pode ser designado como um método manipulador, a ser chamado quando uma solicitação que o método sabe como tratar (chamada de “solicitação de correspondência”) é recebida. Vamos escrever um método manipulador de solicitação de leitura! Aqui está um começo:

![cont1](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/3e39d7e4-6473-42c3-b0bc-c42ac2db2593)

Como o REST diz que os endpoints de leitura devem usar o método HTTP GET, você precisa dizer ao Spring para rotear solicitações para o método apenas nas solicitações GET. Você pode usar a anotação @GetMapping , que precisa do caminho URI:

![cont2](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/5f8fdffc-4639-4b0b-bbfc-4ee4ed10b615)

Spring precisa saber como obter o valor do parâmetro requestedId. Isso é feito usando a anotação @PathVariable. O fato do nome do parâmetro corresponder ao texto {requestedId} dentro do parâmetro @GetMapping permite que o Spring atribua (injete) o valor correto à variável requestedId:

![cont3](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/25ea0192-494a-4154-8692-58dc296b3e3f)

REST diz que a resposta precisa conter um Cash Card em seu corpo e um código de resposta 200 (OK). Spring Web fornece a classe ResponseEntity para essa finalidade. Ele também fornece vários métodos utilitários para produzir Entidades de Resposta. Aqui, você pode usar ResponseEntity para criar um ResponseEntity com código 200 (OK) e um corpo contendo um arquivo CashCard. A implementação final é assim:

![cont4](https://github.com/leila-bwt/APIRestComTestFirst/assets/108028195/92ea2684-7208-4947-a083-bb34c02d93b5)



## Escrevendo um Teste para o endpoint GET

Embora @Autowired seja uma forma de injeção de dependência Spring, é melhor usá-lo apenas em testes.