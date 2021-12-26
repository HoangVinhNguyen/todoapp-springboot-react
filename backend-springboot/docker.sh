IMAGE_NAME=todoapp
HOST_PORT=8088

err() {
    echo $* >&2
}

usage() {
    err "$(basename $0): [build|run|all|login]"
}

clean() {
    IMAGE=$(docker ps -a -q --filter ancestor=${IMAGE_NAME} --format="{{.ID}}")

    if ! test -z "$IMAGE"
    then
        docker rm $(docker stop ${IMAGE})
        docker rmi -f ${IMAGE_NAME}
    fi
}

build_docker() {
    docker build -t ${IMAGE_NAME} .
    docker-compose up -d
}

launch() {
    docker run -p ${HOST_PORT}:8088 -d ${IMAGE_NAME}
}

login() {
    docker exec -it $(docker ps -q --filter ancestor=${IMAGE_NAME} --format="{{.ID}}") /bin/bash
}

execute() {
    local task=${1}
    case ${task} in
        build)
            clean
            build_docker
            ;;
        run)
            launch
            ;;
        all)
            clean
            build_docker
            launch
            ;;
        *)
            err "invalid task: ${task}"
            usage
            exit 1
            ;;
    esac
}

main() {
    [ $# -ne 1 ] && { usage; exit 1; }
    local task=${1}
    execute ${task}
}

main $@
