import axios from 'axios';
import React, { useEffect } from 'react'
import { useState } from 'react'
import { Link } from 'react-router-dom';
import "./sidebar.css"
export default function Sidebar() {
  const [cats,setCats] = useState([]);

  useEffect(()=>{
    const getCats = async ()=>{
      const res = await axios.get("http://localhost:7070/api/categories/")
      setCats(res.data)
    };
    getCats();
  },[])
  return (
    <div className='sidebar'>
      <div className="sidebar">
      <div className="sidebarItem">
        <span className="sidebarTitle">ABOUT ME</span>
        <img
          src="https://themegoods-cdn-pzbycso8wng.stackpathdns.com/grandblog/demo/wp-content/uploads/2015/11/aboutme.jpg"
          alt=""
        />
        <p>
          Laboris sunt aute cupidatat velit magna velit ullamco dolore mollit
          amet ex esse.Sunt eu ut nostrud id quis proident.
        </p>
      </div>
      <div className="sidebarItem">
        <span className="sidebarTitle">CATEGORIES</span>
        <ul className="sidebarList">
        {cats.map(c=>(
             <Link to={`/?cat=${c.title}`} className="link">
              <li className="sidebarListItem">{c.title}</li>
             </Link>
          ))}
        </ul>
        </div>
        <div className="sidebarItem">
        <span className="sidebarTitle">FOLLOW US</span>
        <div className='sidebarSocial'>
        <i className="sideIcon fa-brands fa-square-facebook"></i>
        <i className="sideIcon fa-brands fa-square-twitter"></i>
        <i className="sideIcon fa-brands fa-instagram"></i>
        </div>
        </div>
    </div>
    </div>
  )
}
