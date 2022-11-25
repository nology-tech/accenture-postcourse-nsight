interface studentInt {
  photo: string;
  name: string;
  about: string;
  portfolio: string;
  hobbies: string;
  education: [
    {
      title: string;
      provider: string;
      period: string;
    },
    {
      title: string;
      provider: string;
      period: string;
    },
    {
      title: string;
      provider: string;
      period: string;
    }
  ];

  experience: [
    {
      title: string;
      company: string;
      period: string;
      where: string;
      keys: [string, string, string];
    },
    {
      title: string;
      company: string;
      period: string;
      where: string;
      keys: [string, string, string];
    },
    {
      title: string;
      company: string;
      period: string;
      where: string;
      keys: [string, string, string];
    }
  ];
  projects: [
    {
      title: string;
      info: string;
    },
    {
      title: string;
      info: string;
    },
    {
      title: string;
      info: string;
    },
    {
      title: string;
      info: string;
    },
    {
      title: string;
      info: string;
    },
    {
      title: string;
      info: string;
    }
  ];
}

const student = {
  photo:
    "https://as2.ftcdn.net/v2/jpg/00/83/61/67/1000_F_83616717_I00AQOwtsbxasbuCO40KjnWpCY5DmkHv.jpg",
  name: "Lana Ferrari",
  about:
    "Hello, I'm a Junior Software Developer with Marketing and Content Design background. For the past year, I've been studying web development, and before I joined Nology, I graduated from a Full-stack Engineering Bootcamp. I enjoy general problem-solving and learning new things every day.",
  portfolio: "https://github.com/lanaFerrari",
  hobbies:
    "I like watching documentaries, travelling to new places and trying new cuisines.",
  education: [
    {
      title: "AWS Certified Cloud Practitioner",
      provider: "AWS",
      period: "2020",
    },
    {
      title: "Full stack Software Developer Bootcamp",
      provider: "Boolean UK",
      period: "2021",
    },
    {
      title: "AA Marketing",
      provider: "UNIP, DF, Brazil",
      period: "2017 - 2019",
    },
  ],
  experience: [
    {
      title: "Junior Software Developer",
      company: "Accenture",
      period: "May - Oct 2022",
      where: "London",
      keys: [
        "Worked with Front-End Development",
        "Designing APIs in Java",
        "Pair programming",
      ],
    },
    {
      title: "Content Designer",
      company: "Care Dial",
      period: "2020 - 2021",
      where: "London",
      keys: [
        "Designing the Company’s Website",
        "Creation of a Tone of Voice Guideline ",
        "Data-driven and accessible copy ",
      ],
    },
    {
      title: "Creative Copywrite",
      company: "Mccann",
      period: "2019 - 2020",
      where: "London",
      keys: [
        "Conceptualized and managed campaigns ",
        "Creation of jingles and promotional videos",
        "Creation of digital and offline content ",
      ],
    },
  ],
  projects: [
    {
      title: "HTML/ CSS Portfolio Website",
      info: "Built using correct version control and modern coding​ standards, and deployed live using a CI pipeline.",
    },
    {
      title: "JavaScript Game Project",
      info: "A browser-based game using modern JavaScript​ programming techniques and DOM manipulation.",
    },
    {
      title: "React API Project",
      info: "A React application that pulls data from an external public API and presents it in a dashboard or multipage layout.",
    },
    {
      title: "Java Project",
      info: "A Java Object Oriented application making use of multiple models and classes to run complex game logic from the command line.",
    },
    {
      title: "Spring Boot Project",
      info: "A properly architected and documented API following all modern design patterns, built using Spring and deployed using GCP.",
    },
    {
      title: "Final Client Project Delivery",
      info: "A real-life MVP for a development project, delivered​ as a Scrum team to a client who will use it for future​ development. It will include a React front-end and Spring back-end integration, including authentication and CI deployment to GCP.​",
    },
  ],
};

export default student;
