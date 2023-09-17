import Home from "./pages/home/Home";
import TopBar from "./components/TopBar/TopBar";
import Single from "./pages/single/Single";
import Write from "./pages/write/Write";
import Settings from "./pages/settings/Settings";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import { Routes, Route } from "react-router-dom";
import { useContext } from "react";
import { Context } from "./context/Context";
function App() {
const {user} = useContext(Context)
  return (
    <>
    <TopBar/>
    <Routes>
    <Route exact path="/" element={<Home />}>
    </Route>
    <Route path="/register" element={user?<Home/>:<Register />}>
    </Route>
    <Route path="/login" element={user?<Home/>:<Login />}>
    </Route>
    <Route path="/write" element={user?<Write/>:<Register />}>
    </Route>
    <Route exact path="/settings" element={user?<Settings />:<Register/>}>
    </Route>
    <Route exact path="/post/:postId" element={<Single />}>
    </Route>
  </Routes>
  </>
  );
}

export default App;
