import { useState } from "react";
import { useDispatch } from "react-redux";
import { addListTodo } from '../../redux/actions';
import { axiosInstance } from '../../utils';


export default function TopBar() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loginDone, setLoginDone] = useState(false);
    const [userLogin, setUserLogin] = useState('');
    const [userId, setUserId] = useState('');
    const dispatch = useDispatch();

    const handleLoginButton = async () => {
        try {
            const res = await axiosInstance.post('/auth/login', { username, password });
            setLoginDone(true);
            setUserLogin(res.data.username);
            setUserId(res.data.id);
            localStorage.account_accessToken = res.data.accessToken;
            localStorage.account_userID = res.data.id;
            localStorage.account_refreshToken = res.data.refreshToken;
            localStorage.account_username = res.data.username;
            localStorage.account_type = res.data.roles;
            const resTodo = await axiosInstance.get('/user/todo/all/' + res.data.id,
                { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            dispatch(addListTodo(resTodo.data));
        } catch (error) {
            console.log(error.response.data);
        }
    }
    const handleLogoutButton = async () => {
        try {
            await axiosInstance.post('/auth/logout', { userId });
            setLoginDone(false);
            setUsername('');
            setPassword('');
            localStorage.clear();
            dispatch(addListTodo([]));
        } catch (error) {
            console.log(error.response.data);
        }
    }
    const handleUsernameInputChange = (e) => {
        setUsername(e.target.value);
    }
    const handlePasswordInputChange = (e) => {
        setPassword(e.target.value);
    }

    return (
        <div className="row d-flex">
            {
                !loginDone
                && <input type={"text"} className="form-control" placeholder="Username..."
                    value={username} onChange={handleUsernameInputChange} />
            }
            {
                !loginDone
                && <input type={"password"} className="form-control" placeholder="Password..."
                    value={password} onChange={handlePasswordInputChange} />
            }
            {
                !loginDone
                && <button className="btn btn-success" onClick={handleLoginButton}>Login</button>
            }
            {
                loginDone
                && <h4>Welcome {userLogin}</h4>
            }
            {
                loginDone
                && <button className="btn btn-secondary" onClick={handleLogoutButton}>Logout</button>
            }

        </div>
    );
}
