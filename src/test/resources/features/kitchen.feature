Feature: Kitchen

  Scenario: Kitchen Criacao Pedido
    Given Cria um pedido na cozinha
    When ele passa o id 1
    Then deve criar o pedido AWAITING_PRODUCTION

  Scenario: Kitchen pedido em Production
    Given Um pedido valido em AWAITING_PRODUCTION
    When ele passa o id 1
    Then deve mover o pedido para PRODUCTION
