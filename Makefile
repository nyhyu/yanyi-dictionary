.PHONY: build check_env test verify upload clean all

BUILD_IMAGE ?= 192.168.104.19:5000/logbook/centos6-maven
RUNTIME_IMAGE ?= 192.168.104.19:5000/logbook/centos6-runtime
JSS_PROFILE ?= default

CWD := $(shell pwd)
CONTAINER_NAME := $(shell basename $(CWD))-$(shell date '+%F-%H.%M.%S')

DOCKER_RUN_OPTS := \
	--rm=true \
	--publish-all=true \
	--name $(CONTAINER_NAME) \
	--hostname $(CONTAINER_NAME) \
	--volume=/etc/localtime:/etc/localtime:ro \
	--volume=$(CWD):$(CWD) \
	--workdir=$(CWD)\
	--add-host=artifactory.360buy-develop.com:192.168.168.97

build: clean
	docker run $(DOCKER_RUN_OPTS) $(BUILD_IMAGE) ./build.sh

upload:
	./upload.sh *.bin --profile $(JSS_PROFILE)

clean:
	rm -f *.bin

all: build
