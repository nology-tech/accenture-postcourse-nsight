import React from 'react'
import "./NavBar.scss"
import logo from "../../assets/logo.png"

const NavBar: React.FC<{}> = () => {
  return (
    <nav className="navbar">
      <div className="logo-container">
        <img src={ logo } alt="nology logo" className=" logo-container_logo" />
      </div>
      <hr className = "line" />
      <ul>
        <li>Courses</li>
        <li>Students</li>
        <li>Enrollment</li>
        <hr className = "line" />
        <li>Settings</li>
      </ul>
    </nav>
  )
}

export default NavBar;  