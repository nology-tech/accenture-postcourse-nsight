
import CoursesNav from "../../components/CoursesNav/CoursesNav";
import Layout from "../../components/Layout/Layout";
import "./Courses.scss"

export const Courses = () => {
  return (
    <div className="home">
      <Layout>
        <CoursesNav />
      </Layout>
    </div>
  );
};

export default Courses;
