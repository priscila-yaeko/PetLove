Feature: Cadastro de um gato

  Scenario: Cadastrar um gato

    Given que estou na tela principal do sistema de cadastro
    And visualizo o formulário de cadastro disponível
    When preencho todos os campos obrigatórios com as informações do pet
    And seleciono a opção "Gato" como tipo de pet
    Then clico no botão "Cadastrar"
    And vejo a mensagem de sucesso confirmando o cadastro