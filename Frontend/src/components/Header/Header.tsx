import React from 'react';
import Button from '../Button/Button';
import "./Header.scss";

const Header: React.FC = () => {
    return <div className='container'>
        <div className='grid'>        
        <h1>Hello </h1>
        {/* going to pass props here */}
        <Button
        label="Create"
        onClick={() => {
          console.log("button clicked");
        }}
      />
      </div>

    </div>
}

export default Header;