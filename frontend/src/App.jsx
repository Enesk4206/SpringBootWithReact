import {BrowserRouter as Router, Route, Routes} from "react-router-dom"
import Homepage from './pages/HomePage'
import Login from './pages/Login'
import Register from './pages/Register'
import Navbar from "./components/Navbar"
import Footer from "./components/Footer"
const App = () => {
  return (
    <Router>
      <div className='app'>
        <Navbar/>
        <main style={{minHeight:"80vh"}}>
          <Routes>
            <Route path='/' element={<Homepage/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/login" element={<Login/>}/>
            {/* <Route path='/categories' element={<CategoryPage/>}/> */}
          </Routes>
        </main>
        <Footer/>
      </div>
    </Router>
  )
}

export default App