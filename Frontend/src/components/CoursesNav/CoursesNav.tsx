import Button from "../Button/Button";
import "./CoursesNav.scss"

export const CoursesNav = () => {
    return (
      <div className="courses-nav-container">
        <h1>Courses</h1>
        <ul className="list">
            <li>All</li>
            <li>Full-Time</li>
            <li>Corporate</li>
        </ul>
        <Button
          className="button"
          label="Create"
          onClick={() => {
            console.log("button clicked");
          }} />
      </div>
    );
  };
  
  export default CoursesNav;