import React from 'react'
import "./NavBar.scss"
import logo from "../../assets/logo.png"
import { RiPagesLine } from "react-icons/ri";
import { IoIosPeople } from "react-icons/io";
import { FaAward } from "react-icons/fa";
import { SlSettings } from "react-icons/sl";
import { Link } from "react-router-dom";


const NavBar: React.FC<{}> = () => {
  return (
    <nav className="navbar">
      <div className="logo-container">
        <Link to="/Courses"><img src={ logo } alt="nology logo" className= "logo-container_logo" /></Link>
      </div>
      <hr className = "line" />
      <ul>
        <div className = "list-container">
        <Link to="/Courses"><div className = "nav-list"><RiPagesLine /><li>Courses</li></div></Link>
        <Link to="/Students"><div className = "nav-list"><IoIosPeople /><li>Students</li></div></Link>
        <Link to="/Enrollment"><div className = "nav-list"><FaAward /><li>Enrollment</li></div></Link>
          <hr className = "line" />
        <Link to="/Settings"><div className = "nav-list"><SlSettings /><li>Settings</li></div></Link>
        </div>
      </ul>
    </nav>
  )
}

export default NavBar;  