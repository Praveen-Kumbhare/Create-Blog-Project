import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Context } from "../../context/Context";
import "./write.css";

export default function Write() {
  const [categories, setCategories] = useState([]);
  const [selectedOption, setSelectedOption] = useState("");
  const { user } = useContext(Context);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [file, setFile] = useState("");

  useEffect(() => {
    const getCategory = async () => {
      const res = await axios.get("http://localhost:7070/api/categories/");
      setCategories(res.data);
    };
    getCategory();
  }, []);
  const handleSubmit = async (e) => {
    console.log(selectedOption);
    e.preventDefault();
    const newPost = {
      title,
      content,
    };

    try {
      const res = await axios.post(
        `http://localhost:7070/api/user/${user.user.userId}/category/${selectedOption}/blogs/`,
        newPost
      );
      if (file) {
        const data = new FormData();
        data.append("image", file);
        try {
          await axios.post(
            `http://localhost:7070/api/blog/image/upload/${res.data.id}`,
            data,
            {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            }
          );
        } catch (err) {}
      }
      window.location.replace("/post/" + res.data.id);
    } catch (err) {}
  };
  return (
    <div className="write">
      {file && (
        <img className="writeImg" src={URL.createObjectURL(file)} alt="" />
      )}
      <form className="writeForm" onSubmit={handleSubmit}>
        <div className="writeFormGroup">
          <label htmlFor="fileInput">
            <i className="writeIcon fas fa-plus"></i>
          </label>
          <input
            id="fileInput"
            type="file"
            style={{ display: "none" }}
            onChange={(e) => setFile(e.target.files[0])}
          />
          <input
            className="writeInput"
            placeholder="Title"
            type="text"
            onChange={(e) => setTitle(e.target.value)}
            autoFocus={true}
          />
        </div>
        <div className="writeFormGroup">
          <label htmlFor="category">Select a category:</label>
          <select
            id="category"
            onChange={(e) => setSelectedOption(e.target.value)}
          >
            <option value="">Select a category...</option>
            {categories.map((category) => (
              <option key={category.categoryId} value={category.categoryId}>
                {category.title}
              </option>
            ))}
          </select>
        </div>
        <div className="writeFormGroup">
          <textarea
            className="writeInput writeText"
            placeholder="Tell your story..."
            type="text"
            onChange={(e) => setContent(e.target.value)}
            autoFocus={true}
          />
        </div>
        <button className="writeSubmit" type="submit">
          Publish
        </button>
      </form>
    </div>
  );
}
