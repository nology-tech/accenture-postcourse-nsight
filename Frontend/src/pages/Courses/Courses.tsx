import Button from "../../components/Button/Button";
import Layout from "../../components/Layout/Layout";
import "./Courses.scss"

export const Home = () => {
  return (
    <div className="home">
      <Layout>
        
        <Button
          className="button"
          label="Create"
          onClick={() => {
            console.log("button clicked");
          }}
        />
      </Layout>
    </div>
  );
};

export default Home;
