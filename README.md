# Dungeons Table
<p style="text-align: justify;">Desenvolvi esta API em <b>Spring Boot</b> durante minha pós-graduação. <b>Dungeons Table</b> é um projeto pessoal que vem ganhando forma aos poucos. Meu objetivo é que, ao final da pós, ele se torne um website de apoio para mestres de RPG, um espaço para centralizar ideias e informações de suas mesas. Durante o desenvolvimento da API tive algumas ideias a mais, para ser utilizado por jogadores também, mas o <b>foco principal</b> é o apoio aos mestres.
<br>Como mestre iniciante, sei que conduzir uma mesa exige lidar constantemente com uma enxurrada de informações. É preciso ter muitos dados à disposição o tempo todo. 
A proposta do Dungeons Table é oferecer tudo de forma prática e acessível: desde informações sobre os jogadores até detalhes do mundo, além de registros de sessões passadas e das que ainda estão por vir. </p>


## Índice
- [Estrutura](#estrutura)
- [Instalação](#instalação)
- [Uso](#uso)
- [Licença](#licença)

## Estrutura
```plaintext
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── edu/
│   │   │           └── utfpr/
│   │   │               └── dungeontable/
│   │   │                   ├── config/       # Configurações gerais do projeto
│   │   │                   ├── controller/   # Controllers que recebem as requisições HTTP
│   │   │                   ├── exception/    # Tratamento de exceções
│   │   │                   ├── model/        # Entidades e modelos de dados
│   │   │                   │   ├── interactions/ # Modelos de interações entre entidades
│   │   │                   │   ├── table/        # Modelos referentes aos principais items que compõe uma mesa de RPG
│   │   │                   │   ├── tools/        # Modelos de ferramentas dos players
│   │   │                   │   └── vo/           # Objetos de transferência de dados (VOs)
│   │   │                   ├── repository/   # Repositórios para acesso ao banco de dados
│   │   │                   ├── security/     # Configurações de segurança e autenticação
│   │   │                   └── service/      # Serviços com a lógica de negócio
│   │   └── resources/
│   │       └── application.properties  # Configurações do Spring Boot
└── test/
    └── java/
        └── br/
            └── edu/
                └── utfpr/
                    └── dungeontable/
                        ├── controller/   # Testes para controllers
                        └── service/      # Testes para serviços
```

## Instalação
Foi utilizado para o desenvolvimento a `IDE IntelliJ IDEA Community Edition`. Clone o projeto utilizando esta IDE e baixe todas as dependencias que constam no `pom.xml`.<br>
A porta padrão é a `9995`, o banco de dados utilizado é o H2. Fique livre para alterar estas informações, todas constam no arquivo `application.properties`, dentro da pasta `resources`.

## Uso
Após rodar o servidor, acesse `http://localhost:9995/swagger-ui/index.html` para ter acesso a todos os endpoints. Pode ser utilizado também o Postman, para utilizar as rotas corretamente deve-se utilizar primeiro o endpoint `/api/register` e depois logar com `/api/login`.



## Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
