import React from 'react'
import "./NavBar.scss"
import * as data from "./links.json"
const linksString = JSON.stringify(data);
const links = JSON.parse(linksString).links;

type Link = {
  label: string;
  href: string;
}

const Links: React.FC<{ links: Link[] }> = () => {
  return (
    <div className="links-container">
        {links.map((link: Link) => {
            return (
                <div key={link.href} className="link">
                  <a href={link.href}>
                    {link.label}
                  </a>
                </div>
             )
            }
        )}
      </div>
  )
}

const NavBar: React.FC<{}> = () => {
  return (
    <nav className="navbar">
      <div className="logo-container">
        <div className="logo-container_logo">Logo</div>
      </div>
      <hr />
      <Links links={links} />
    </nav>
  )
}

export default NavBar;  