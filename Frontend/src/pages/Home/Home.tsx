import Button from "../../components/Button/Button";
import Layout from "../../components/Layout/Layout";

// to see this page component action, run the app with nothing appended to the URL 🧐
export const Home = () => {
  return (
    <Layout>
      
      <Button
        label="Create"
        onClick={() => {
          console.log("button clicked");
        }}
      />
    </Layout>
  );
};

export default Home;
