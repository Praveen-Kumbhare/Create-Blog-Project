import "./singlepost.css";
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { Context } from "../../context/Context";
export default function SinglePost() {
  const location = useLocation();
  const path = location.pathname.split("/")[2];
  const [post, setPost] = useState([]);
  const { user } = useContext(Context);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [updateMode, setUpdateMode] = useState(false);
  const PF = "http://localhost:7070/api/blog/image/";
  useEffect(() => {
    const getPost = async () => {
      const res = await axios.get("http://localhost:7070/api/blogs/" + path);
      setPost(res.data);
      setTitle(res.data.title);
      setContent(res.data.content);
    };
    getPost();
  }, [path]);
  const handleDelete = async () => {
    try {
      await axios.delete("http://localhost:7070/api/blogs/" + path);
      window.location.replace("/");
    } catch (error) {}
  };
  const handleUpdate = async () => {
    try {
      await axios.put("http://localhost:7070/api/blogs/" + path, {
        title: title,
        content: content,
      });
      setUpdateMode(false);
    } catch (error) {}
  };
  return (
    <div className="singlePost">
      <div className="singlePostWrapper">
        {post.imageName && (
          <img className="singlePostImg" src={PF + post.imageName} alt="" />
        )}
        {updateMode ? (
          <input
            type="text"
            value={title}
            className="singlePostTitleInput"
            autoFocus
            onChange={(e) => setTitle(e.target.value)}
          />
        ) : (
          <h1 className="singlePostTitle">
            {title}
            {post.user?.username === user.user?.username && (
              <div className="singlePostEdit">
                <i
                  className="singlePostIcon far fa-edit"
                  onClick={() => setUpdateMode(true)}
                ></i>
                <i
                  className="singlePostIcon far fa-trash-alt"
                  onClick={handleDelete}
                ></i>
              </div>
            )}
          </h1>
        )}
        <div className="singlePostInfo">
          <span>
            <b className="singlePostAuthor">
              Author:
              <Link to={`/?user=${post.user?.username}`}>
                {post.user?.username}
              </Link>
            </b>
          </span>
          <span>{new Date(post.addedDate).toDateString()}</span>
        </div>
        {updateMode ? (
          <textarea
            className="singlePostDescInput"
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
        ) : (
          <p className="singlePostDesc">{post.content}</p>
        )}
        {updateMode && (
          <button className="singlePostButton" onClick={handleUpdate}>
            Update
          </button>
        )}
      </div>
    </div>
  );
}
