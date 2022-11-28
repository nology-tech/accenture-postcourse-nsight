import { Navigate, Route, Routes } from "react-router-dom";
import Button from "./components/Button/Button";
import NavBar from "./pages/NavBar/NavBar";
import NotFound from "./pages/NotFound/NotFound";
import "./styles/main.scss";
import Courses  from "./pages/Courses/Courses"
import { Students } from "./pages/Students/Students";
import { Enrollment } from "./pages/Enrollment/Enrollment";

const App = () => {
  return (
    <>
      <NavBar />
      <Courses />
      <Routes>
        {/* Routes here */}
        <Route path="/Courses" element={<Courses />} />
        <Route path="/Students" element={<Students />} />
        <Route path="/Enrollment" element={<Enrollment />} />

        {/* Any redirects */}
        <Route path="/" element={<Navigate replace to="/Courses" />} />

        {/* Last is a catch-all route that will show a not found view */}
        {/* <Route path="*" element={<NotFound />} /> */}
     </Routes>
    </>
  );
};

export default App;
