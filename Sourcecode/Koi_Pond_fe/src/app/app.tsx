import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "app/home";
import Layout from "components/layout/layout.tsx";
import Login from "./login";

function App() {
  return (
    <Router>
      <Routes>
      <Route
          path="/"
          element={
            <Layout>
              <Home />
            </Layout>
          }
        />
            <Route path="/login" element={<Login/>}/>
            
          </Routes>
    </Router>
  );
}

export default App;
