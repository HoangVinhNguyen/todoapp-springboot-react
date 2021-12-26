import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addListTodo } from '../../redux/actions';
import { axiosInstance } from '../../utils';

export default function AddTodo() {

    const [todoName, setTodoName] = useState('');

    const dispatch = useDispatch();

    const handleInputChange = (e) => {
        setTodoName(e.target.value);
    }

    const handleAddButtonClick = async () => {
        try {
            await axiosInstance.post('/user/todo',
                {
                    userId: localStorage.account_userID,
                    name: todoName,
                    completed: false,
                }, { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });

                const resTodo = await axiosInstance.get('/user/todo/all/' + localStorage.account_userID,
                { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            dispatch(addListTodo(resTodo.data));
        } catch (error) {
            console.log(error.response.data);
        }
        // dispatch(addTodo({
        //     id: uuidv4(),
        //     name: todoName,
        //     completed: false,
        // }));
        setTodoName('');
    }

    return (
        <div className="row">
            <h3>Add Task</h3>
            <div className="fg">
                <input type="text" value={todoName} onChange={handleInputChange} />
                <button className="btn btn-primary" onClick={handleAddButtonClick}>Add</button>
            </div>
        </div>
    );
}
