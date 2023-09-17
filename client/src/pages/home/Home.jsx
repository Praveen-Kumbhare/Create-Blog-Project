import React, { useEffect, useState } from "react";
import Header from "../../components/header/Header";
import Posts from "../../components/posts/Posts";
import Sidebar from "../../components/sidebar/Sidebar";
import { useSearch } from "../../context/SearchContext";
import "./home.css";
import axios from "axios";
import { useLocation } from "react-router-dom";

export default function Home() {
  const [posts, setPosts] = useState([]);
  const { search } = useLocation();
  const { searchQuery } = useSearch();
  useEffect(() => {
    const fetchPosts = async () => {
      const res = await axios.get(
        "http://localhost:7070/api/blogs/search/" + searchQuery
      );
      setPosts(res.data.content);
      if (searchQuery) setPosts(res.data);
    };
    fetchPosts();
  }, [searchQuery]);
  return (
    <div>
      <Header />
      <div className="home">
        <Posts posts={posts} />
        <Sidebar />
      </div>
    </div>
  );
}
