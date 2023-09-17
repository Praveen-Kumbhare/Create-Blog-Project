import "./post.css"
import { Link } from "react-router-dom";
export default function Post({post}) {
  const PF = "http://localhost:7070/api/blog/image/";
  return (
    <div className="post">
      {post.imageName && (
         <img
          className="postImg"
          src={PF+post.imageName}
          alt=""
        />
      )}
      <div className="postInfo">
        <div className="postCats">
          {post.category.title}
        {/* {post.data.category.map(c=>(
           <span className="postCat">{c.category.title}</span>
        ))}    */}
        </div>
        <Link className="link" to={`/post/${post.id}`}>
        <span className="postTitle">{post.title}</span>
        </Link>
        <hr/>
        <span className="postDate">{new Date(post.addedDate).toDateString()}</span>
      </div>
      <p className="postDesc">
        {post.content}
      </p>
    </div>
  )
}
