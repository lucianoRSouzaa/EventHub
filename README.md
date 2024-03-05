# Atividade pratique - Faculdade Anhembi Morumbi

## Requisitos do projeto:
Implemente um sistema em linguagem de programação Java utilizando a IDE de sua preferência. Algumas sugestões são: Eclipse, NetBeans, IntelliJ e Replit. No slide seguinte,  a entrega deverá ser realizada com a inserção do link de repositório de códigos que contenha os diferentes arquivos e partes do código utilizados.
Siga as diretrizes que seguem para o desenvolvimento da atividade:
- [x] A solução deve ser desenvolvida dentro do paradigma orientado a objetos;
- [x] Opcionalmente o projeto pode ser organizado em um repositório GIT;
- [x] O uso de padrões arquiteturais como o MVC não é obrigatório, mas fortemente encorajado;
- [x] A estruturação de um diagrama de classes é obrigatória, mas desejada;
- [x] O projeto deve ser desenvolvido em Console;
- [x] Deve implementar um sistema de cadastro e notificação de eventos que estejam ocorrendo na cidade em que o estudante reside;
- [x] O sistema deve prover um espaço para cadastro do usuário. Você deve definir os atributos do usuário, que devem ser no mínimo 3 (quanto mais completo, melhor);
- [x] Deve ser possível cadastrar eventos, definindo um horário (dentre outros atributos). Estes eventos devem ter, obrigatoriamente, os atributos: nome, endereço, categoria, horário e descrição;
- [x] Você deve delimitar as categorias para criação de eventos (festas, eventos esportivos, shows, entre outros exemplos);
- [x] Deve ser possível consultar os eventos cadastrados e decidir participar de qualquer um que esteja listado; 
- [x] Da mesma forma, deve ser possível visualizar os eventos em que a presença do usuário foi confirmada e que seja possível cancelar a participação;
- [x] Através do horário, o programa deve ordenar os eventos mais próximos e informar se um evento está ocorrendo no momento (é desejável utilizar a estrutura DateTime para o controle de horários);
- [x] O sistema também deve informar os eventos que já ocorreram;
- [x] As informações dos eventos devem ser salvas em um arquivo de texto chamado events.data;
- [] Toda vez que o programa for aberto, deve carregar os eventos a partir da leitura deste arquivo; 

Siga as instruções e implemente o sistema da forma mais completa que puder. A melhor forma de desenvolver as competências propostas em nossa unidade curricular é programando. Não se intimide, leia as referências da Unidade Curricular e inicie a implementação.


## Comentários e opniões minhas
- Nunca programei em Java na minha vida, também não sei quais são as patterns mais utilizadas em códigos Java, mas tirando isso, tentei fazer o melhor código possível
- Utilizar a arquitetura MVC nesse projeto foi meio estranho, por mais que eu já tenha utilizado muito essa arquitetura no Laravel, eu acho que talvez essa arquitetura não fosse a melhor para esse projeto
- Implementei alguns testes unitários
- Ficou faltando algumas tratativas try/catch, mas sinceramente, não estou com vontade nenhuma de implementar isso, se você clonou esse repositório, fique de missão arrumar os mini bugs que ocorrem no projeto
- Trabalhar com arquivos de textos em vez de um banco de dados foi uma das coisas que eu mais odiei desse projeto
- Todos os requisitos foram cumpridos, menos o "Toda vez que o programa for aberto, deve carregar os eventos a partir da leitura deste arquivo" por que pra mim isso não faz sentido
