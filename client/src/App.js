import { Route, Routes } from "react-router-dom";
import { PAGE_PATH } from "./utils/constants";
import { HomePage, LoginPage, SignupPage } from "./pages";

function App() {
  return (
    <Routes>
      <Route path={PAGE_PATH.HOME} element={<HomePage />} />
      <Route path={PAGE_PATH.SIGNUP} element={<SignupPage />} />
      <Route path={PAGE_PATH.LOGIN} element={<LoginPage />} />
    </Routes>
  );
}

export default App;
