<h1 align = "center"><img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>
<i>"Game Room"</i> Project <img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>
</h1>

# Content:

- [ER Diagram](#er-diagram)
- [DAO Methods](#dao-methods)
  - [Pc methods](#pc-methods)
  - [Pc Detail methods](#pcdetail-methods)
  - [Player methods](#player-methods)

# ER Diagram

<img src="./other/er.png" />

| Relationship     | Association    | Fetch Type | Description                                                                                                                            |
|------------------|----------------|------------|----------------------------------------------------------------------------------------------------------------------------------------|
| pcs - pc_details | Bi-directional | LAZY       | If pc deleted also delete his pc details. If pc details deleted, don't delete pc.<br/> If we save pc, also save his details if linked. |
| pcs - players    | Bi-directional | LAZY       | If player deleted, don't delete pc. If pc deleted also delete player.<br/> If we save player, don't save pc.                           |

# DAO Methods

## Pc methods

| Method                                          | Return    |
|-------------------------------------------------|-----------|
| savePcWithPcDetails(Pc pc, PcDetails pcDetails) | boolean   |
| savePc(Pc pc)                                   | boolean   |
| findPcById(Long id)                             | Pc        |
| findAllPcs()                                    | List< Pc> |
| deletePcById(Long id)                           | boolean   |
| updatePc(Pc pc)                                 | Pc        |

## PcDetail methods

| Method |
|--------|
| /      |

PcDetail methods not implemented - reason: cascading.

## Player methods

| Method                     | Return        |
|----------------------------|---------------|
| savePlayer(Player player)  | boolean       |
| findPlayerById(Long id)    | Player        |
| findAllPlayers()           | List< Player> |
| deletePlayerById(Long id)  | boolean       | 

