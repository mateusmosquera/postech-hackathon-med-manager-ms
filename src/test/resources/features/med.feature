Feature: Gerenciamento de meds via HTTP

   @tagPost
   Scenario Outline: : Criar um novo med
    Given que o med envia uma solicitacao para criar um novo med, com cpf "<cpf>", com nome <nome> e email <email>
    When med recebe a solicitacao com code status <status>
    Then o med recebe a resposta, com cpf "<cpf>", com nome <nome> e email <email>


     Examples:
     | cpf               | nome             | email              | status |
     |  686.524.400-18   | Med Teste1   | med1@teste.com | 201    |
     |  123.456.789-09   | Med Teste    | med@teste.com  | 400    |

  @tagGet
  Scenario Outline: Recuperar informacoes de um med pelo CPF
    Given que o med envia uma solicitacao para recuperar informacoes de um med pelo CPF
    When  med recebe as informacoes com code status <status>
    Then recebe as informacoes com a resposta

    Examples:
      | status |
      | 200    |