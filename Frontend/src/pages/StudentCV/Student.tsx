import About from "../../components/CV/About/About";
import Education from "../../components/CV/Education/Education";
import Header from "../../components/CV/Header/Header";
import Hobbies from "../../components/CV/Hobbies/Hobbies";
import Projects from "../../components/CV/Projects/Projects";
import Skills from "../../components/CV/Skills/Skills";
import Work from "../../components/CV/Work/Work";
import student from "../../data/student";
import "./Student.css";

const StudentCV = () => {
  //   interface student {
  //     name: string;
  //     photo: string;
  //     about: string;
  //     education: string;
  //     portfolio: string;
  //     hobbies: string;
  //     experiences: string;
  //   }

  return (
    <div id="cv">
      {/* <div className="cv-grid">
        <Header />
        <About
          photo={student.photo}
          name={student.name}
          about={student.about}
        />
        <Skills portfolio={student.portfolio} />
        <Education education={student.education} />
        <Hobbies hobbies={student.hobbies} />
        <Projects />
        <Work experiences={student.experience} />
      </div> */}
    </div>
  );
};

export default StudentCV;
