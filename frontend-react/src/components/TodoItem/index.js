import { useState } from "react";
import { useDispatch } from "react-redux";
import { addListTodo } from "../../redux/actions";
import { axiosInstance } from '../../utils';


export default function TodoItem({ id, name, completed }) {

    const [editButton, setEditButton] = useState(false);
    const [nameEdit, setNameEdit] = useState(name);

    const dispatch = useDispatch();

    const handleDoneButtonClick = async () => {
        try {
            await axiosInstance.post('/user/todo/completed',
                {
                    userId: localStorage.account_userID,
                    id: id,
                    completed: !completed
                }, { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            // dispatch(
            //     toggleTodoStatus(res.data.id)
            // );
            const resTodo = await axiosInstance.get('/user/todo/all/' + localStorage.account_userID,
                { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            dispatch(addListTodo(resTodo.data));
        } catch (error) {
            console.log(error.response.data);
        }

    }
    const handleDeleteButtonClick = async () => {
        try {
            await axiosInstance.post('/user/todo/delete',
                {
                    id: id,
                }, { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            // dispatch(
            //     deleteTodo(res.data.id)
            // );
            const resTodo = await axiosInstance.get('/user/todo/all/' + localStorage.account_userID,
                { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            dispatch(addListTodo(resTodo.data));
        } catch (error) {
            console.log(error.response.data);
        }
        // dispatch(
        //     deleteTodo(id)
        // );
    }
    const handleEditButtonClick = () => {
        setEditButton(!editButton);
    }
    const handleUpdateButtonClick = async () => {
        setEditButton(!editButton);
        try {
            await axiosInstance.post('/user/todo/update',
                {
                    userId: localStorage.account_userID,
                    id: id,
                    name: nameEdit
                }, { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            // dispatch(
            //     updateTodo({ id: res.data.id, name: res.data.name })
            // );
            const resTodo = await axiosInstance.get('/user/todo/all/' + localStorage.account_userID,
                { headers: { "Authorization": `Bearer ${localStorage.account_accessToken}` } });
            dispatch(addListTodo(resTodo.data));
        } catch (error) {
            console.log(error.response.data);
        }

    }
    const handleChangeNameEdit = (e) => {
        setNameEdit(e.target.value);
    }

    return (
        <li className={completed ? 'done' : ''}>
            {
                !editButton
                && name
            }
            {
                editButton
                && <input type="text" className="form-control" value={nameEdit} onChange={handleChangeNameEdit} />
            }
            {
                !completed
                && !editButton
                && <button className="btn btn-warning" onClick={handleEditButtonClick}>Edit</button>
            }
            {// open for edit field.
                !completed
                && editButton
                && <button className="btn btn-primary" onClick={handleUpdateButtonClick}>Update</button>
            }
            {
                !completed
                && <button className="btn btn-success" onClick={handleDoneButtonClick}>Done</button>
            }
            {
                !completed
                && <button className="btn btn-danger" onClick={handleDeleteButtonClick}>Delete</button>
            }
        </li>
    );
}
