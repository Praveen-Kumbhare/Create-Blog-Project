import React, { useContext } from "react";
import "./topbar.css";
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import { useSearch } from "../../context/SearchContext";
export default function TopBar() {
  const { user, dispatch } = useContext(Context);
  const { searchQuery, setSearchQuery } = useSearch();
  const handleInputChange = (e) => {
    setSearchQuery(e.target.value);
  };
  const handleLogout = () => {
    dispatch({ type: "LOGOUT" });
  };
  return (
    <div className="top">
      <div className="topLeft">
        <i className="topIcon fa-brands fa-square-facebook"></i>
        <i className="topIcon fa-brands fa-square-twitter"></i>
        <i className="topIcon fa-brands fa-instagram"></i>
      </div>
      <div className="topCenter">
        <ul className="topList">
          <li className="topListItem">
            <Link className="link" to={"/"}>
              HOME
            </Link>
          </li>
          <li className="topListItem">
            <Link className="link" to={"/"}>
              ABOUT
            </Link>
          </li>
          <li className="topListItem">
            <Link className="link" to={"/"}>
              CONTACT
            </Link>
          </li>
          <li className="topListItem">
            <Link className="link" to={"/write"}>
              WRITE
            </Link>
          </li>
          <li className="topListItem" onClick={handleLogout}>
            {user && "LOGOUT"}
          </li>
        </ul>
      </div>
      <div className="topRight">
        {user ? (
          <img className="topImg" src={user.profilePic} alt="" />
        ) : (
          <ul className="topList">
            <li className="topListItem">
              <Link className="link" to={"/login"}>
                LOGIN
              </Link>
            </li>
            <li className="topListItem">
              <Link className="link" to={"/register"}>
                REGISTER
              </Link>
            </li>
          </ul>
        )}

        <i
          className="topSearchIcon fa-solid fa-magnifying-glass"
          style={{ marginRight: "0.5em" }}
        ></i>
        <input
          type="text"
          placeholder="Search..."
          value={searchQuery}
          onChange={handleInputChange}
        />
      </div>
    </div>
  );
}
