# UFABCalendar

O UFABCalendar é um aplicativo Android desenvolvido para auxiliar estudantes da UFABC a organizarem suas matrículas, planejarem suas grades e acompanharem seu progresso acadêmico de forma eficiente e automatizada, com foco total na experiência de uso em dispositivos móveis.

## Descrição

O UFABCalendar foi criado pensando na rotina dos estudantes, oferecendo uma solução prática e acessível diretamente no celular. Ele surgiu da necessidade de facilitar o processo de montagem da grade horária, que pode ser confuso devido à grande quantidade de matérias, pré-requisitos e sobreposição de horários. Com uma interface mobile amigável, o aplicativo permite que o aluno tome decisões mais estratégicas sobre seu percurso acadêmico em qualquer lugar e a qualquer momento.

## O que o UFABCalendar faz
- Lista todas as matérias ofertadas no quadrimestre, com base nas informações das turmas disponíveis, facilitando a visualização completa das disciplinas em um único lugar.

- Permite aplicar filtros por campus, turno e curso, ajudando o estudante a encontrar rapidamente as matérias que realmente se aplicam à sua realidade acadêmica, evitando confusão com disciplinas de outros cursos ou horários incompatíveis.

- Oferece uma visualização em formato de grade horária, onde o aluno pode selecionar as matérias desejadas e ver como elas se organizam ao longo da semana. Isso permite identificar com facilidade espaços vagos, sobrecargas de horários ou combinações mais leves para cada quadrimestre.

Essa funcionalidade já permite que o estudante comece a montar sua grade com base nas turmas ofertadas, de forma visual e intuitiva, otimizando tempo e reduzindo erros comuns no planejamento manual.

## Em desenvolvimento

- Verificação automática de pré-requisitos das disciplinas.

- Detecção de conflitos de horário entre matérias selecionadas.

- Classificação das matérias como obrigatórias, limitadas ou livres, conforme a grade curricular.

- Acompanhamento do progresso acadêmico do aluno.

## Tecnologias utilizadas

- Kotlin – Linguagem principal de desenvolvimento.
- Android Studio – Ambiente de desenvolvimento.
- MySQL – Banco de dados utilizado para armazenar as informações das turmas e grades curriculares.

## Tecnologias e ferramentas utilizadas

Durante o desenvolvimento do UFABCalendar, foram utilizadas diversas tecnologias e boas práticas de arquitetura para garantir um aplicativo robusto, escalável e eficiente. Abaixo estão os principais recursos aplicados:

- **SQL (MySQL)**  
  Utilizado para estruturar e padronizar o banco de dados remoto, que armazena as informações das turmas ofertadas e grades curriculares. Isso permite que os dados sejam organizados e consultados de forma eficiente, mesmo com grandes volumes de informação.

- **Retrofit**  
  Biblioteca utilizada para realizar requisições HTTP ao banco de dados remoto. Ela facilita a comunicação entre o aplicativo e o servidor, garantindo que os dados sejam recuperados de forma segura, rápida e com tratamento de erros.

- **ROOM (Jetpack)**  
  Implementado como banco de dados local no dispositivo Android. Ele permite que o aplicativo funcione de forma offline e mantenha os dados persistentes entre sessões, com integração direta ao ciclo de vida da aplicação.

- **DataStore (Preferences)**  
  Utilizado para armazenar em cache informações leves como preferências de usuário, filtros e dados temporários. 

- **Arquitetura MVVM (Model-View-ViewModel)**  
  O app segue a arquitetura MVVM para manter uma separação clara de responsabilidades, facilitando a manutenção e escalabilidade do código. Isso garante uma interface reativa, desacoplada da lógica de negócio, com uso eficiente de LiveData e ViewModels.

- **Elementos visuais modernos**  
  - **RecyclerView**: usado para exibir listas de matérias de forma dinâmica e eficiente, com suporte a scroll, filtros e interações do usuário.  
  - **TabLayout com ViewPager**: permite navegação entre dias da semana na grade, proporcionando uma experiência fluida e bem organizada na interface.

## Resultados
  
![Image](https://github.com/user-attachments/assets/45cf5b16-541d-4d6b-9031-a697847c8a01)
