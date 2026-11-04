# Atividade 019 - Padrão Mediator

Refatoração do sistema de sala de chat utilizando o padrão **Mediator**, incluindo o desafio adicional de mensagens privadas.

## Estrutura

| Classe | Papel |
|---|---|
| `ChatMediator` | Mediador: `register`, `send` e `sendPrivate` |
| `ChatRoom` | Mediador concreto: guarda os participantes e concentra as regras de encaminhamento e as validações |
| `ChatUser` | Colega: conhece apenas o mediador, envia por ele e só exibe as mensagens recebidas |
| `Main` | Cria a sala e os usuários, registra-os e demonstra os envios e as recusas |

Regras do `ChatRoom`:
- recusa usuário nulo, registro duplicado da mesma instância e outro usuário com nome já usado (comparação exata);
- recusa mensagens de quem não está registrado;
- envia a mensagem uma vez para cada participante, exceto o próprio remetente;
- na mensagem privada, valida o remetente, se o destinatário existe e se ele é diferente do remetente.

## Como executar

```bash
javac -d out src/*.java
java -cp out Main
```

## Questões para reflexão

**a) Qual problema existente no código inicial foi resolvido com o Mediator?**

Cada usuário guardava uma lista com todos os outros e enviava as mensagens diretamente para eles. Ao entrar alguém novo, era preciso atualizar a lista de todos, e uma configuração incompleta deixava pessoas sem receber mensagens. As regras de envio também ficavam espalhadas pelos usuários. Agora a sala é a única que conhece os participantes e decide para quem cada mensagem vai.

**b) Quais abstrações e classes representam o mediador, o mediador concreto e os colegas participantes na sua implementação?**

- Mediador: `ChatMediator`
- Mediador concreto: `ChatRoom`
- Colegas participantes: `ChatUser` (Ana, Bruno, Carla e Diego na demonstração)

**c) Como a entrada de um novo participante muda em relação ao código inicial e quais dependências deixam de existir entre os usuários?**

Antes, para o Diego entrar, cada usuário precisaria chamar `addContact(diego)` e o Diego precisaria adicionar todos os outros. Agora basta uma chamada: `room.register(diego)`. Os usuários não guardam mais referências uns para os outros. Cada um depende só do mediador, e a ligação de muitos com muitos virou uma ligação de cada usuário com a sala.

**d) Por que as regras de encaminhamento e de mensagens privadas devem ficar no mediador? Qual é o risco de concentrar responsabilidades demais nesse objeto?**

Porque só o mediador conhece todos os participantes. Deixando as regras nele, os usuários continuam simples e as regras ficam num lugar só, fáceis de mudar (por exemplo, para bloquear alguém ou criar grupos). O risco é o mediador crescer demais e virar uma classe enorme que faz tudo (um "god object"), difícil de manter e de testar. Se as regras crescerem muito, vale dividi-las em classes auxiliares.

**e) Embora uma sala de chat e um sujeito observado possam distribuir mensagens a vários objetos, como Mediator e Observer diferem quanto à intenção e à organização das interações?**

No Observer, a comunicação vai num sentido só: um sujeito avisa vários observadores que algo mudou, e os observadores não conversam entre si. No Mediator, vários objetos conversam entre si, nos dois sentidos, e o mediador fica no meio coordenando essa conversa e aplicando regras (quem recebe, validações, mensagens privadas). A intenção do Observer é avisar sobre mudanças de estado. A do Mediator é organizar a interação entre colegas que, sem ele, ficariam dependentes uns dos outros.
