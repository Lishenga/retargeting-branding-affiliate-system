// # name: Consul Server docker image to Amazon ECR

// # on:
// #   push:  
// #     paths:
// #       - consul/consul-server/**
// #     branches:
// #       - main       

// # env:
// #   AWS_DEFAULT_REGION: us-east-1
// #   AWS_DEFAULT_OUTPUT: json   
// #   AWS_ACCOUNT_ID: ${{ secrets.AWS_ACCOUNT_ID }}
// #   AWS_ACCESS_KEY_ID: ${{ secrets.AWS_ACCESS_KEY_ID }}
// #   AWS_SECRET_ACCESS_KEY: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
// #   CONTAINER_IMAGE: consul-server:latest

// # jobs:
// #   build-and-push:
// #     name: Building and pushing image to AWS ECR
// #     runs-on: ubuntu-latest          
// #     steps:
// #       - name: Checkout
// #         uses: actions/checkout@master

// #       - name: Configure AWS Credentials
// #         uses: aws-actions/configure-aws-credentials@v1
// #         with:
// #           aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
// #           aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
// #           aws-region: us-east-1

// #       - name: Login to Amazon ECR
// #         id: login-ecr
// #         uses: aws-actions/amazon-ecr-login@v1

// #       - name: Build and tag the image
// #         id: build-image
// #         run: |
// #           docker build \
// #             -t $CONTAINER_IMAGE \
// #             -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$CONTAINER_IMAGE ./consul/consul-server
// #           echo "::set-output name=image::$AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$CONTAINER_IMAGE"

// #       - name: Push
// #         run: |
// #           docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$CONTAINER_IMAGE

// #       - name: Fill in the new image ID in the Amazon ECS task definition
// #         id: task-def
// #         uses: aws-actions/amazon-ecs-render-task-definition@v1
// #         with:
// #           task-definition: consul/consul-server/task-definition.json
// #           container-name: consul-server
// #           image: ${{ steps.build-image.outputs.image }}

// #       - name: Deploy Amazon ECS task definition
// #         uses: aws-actions/amazon-ecs-deploy-task-definition@v1
// #         with:
// #           task-definition: ${{ steps.task-def.outputs.task-definition }}
// #           service: consul-server
// #           cluster: consul-server
