.PHONY: all stage

DATA_DIR=$(OPENSHIFT_DATA_DIR)
SBT=$(DATA_DIR)/sbt

all: stage

$(SBT):
	curl -L -o $(TMP)/sbt.tgz http://dl.bintray.com/sbt/native-packages/sbt/0.13.5/sbt-0.13.5.tgz
	tar xf $(TMP)/sbt.tgz -C $(TMP)
	mv $(TMP)/sbt $(DATA_DIR)/
	rm -rf $(TMP)/sbt.tgz

stage: $(SBT)
	$(SBT)/bin/sbt -sbt-dir $(DATA_DIR)/.sbt -ivy $(DATA_DIR)/.ivy stage
