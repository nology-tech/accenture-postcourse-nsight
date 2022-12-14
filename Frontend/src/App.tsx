import { Navigate, Route, Routes } from "react-router-dom";
import NavBar from "./pages/NavBar/NavBar";
import "./styles/main.scss";
import Courses  from "./pages/Courses/Courses"
import { Students } from "./pages/Students/Students";
import { Enrollment } from "./pages/Enrollment/Enrollment";
import { Settings } from "./pages/Settings/Settings";

const App = () => {
  return (
    <>
      <NavBar />
      <Routes>
        {/* Routes here */}
        <Route path="/Courses" element={<Courses />} />
        <Route path="/Students" element={<Students />} />
        <Route path="/Enrollment" element={<Enrollment />} />
        <Route path="/Settings" element={<Settings />} />

        {/* Any redirects */}
        <Route path="/" element={<Navigate replace to="/Courses" />} />

     </Routes>
    </>
  );
};

export default App;
