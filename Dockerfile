FROM kitproject/gitpod-flutter-android:stable

# ホストのUIDとGID（適宜置き換える）
ARG HOST_UID=1000
ARG HOST_GID=1000

# Android StudioのダウンロードURL
ARG ANDROID_STUDIO_URL=https://redirector.gvt1.com/edgedl/android/studio/ide-zips/2022.3.1.21/android-studio-2022.3.1.21-linux.tar.gz

USER root

# gitpodユーザーとグループのUIDとGIDをホストのものに変更
RUN groupmod -g ${HOST_GID} gitpod \
    && usermod -u ${HOST_UID} -g ${HOST_GID} gitpod \
    && chown -R ${HOST_UID}:${HOST_GID} /home/gitpod \
    && chown -R ${HOST_UID}:${HOST_GID} /workspace

# 必要なツールのインストールとAndroid Studioのダウンロード・展開
RUN sudo apt-get update && sudo apt-get install -y wget unzip \
    libxtst6 libxrender1 libxi6 libxext6 libgl1-mesa-glx libgl1-mesa-dri \
    && rm -rf /var/lib/apt/lists/* \
    && wget ${ANDROID_STUDIO_URL} -O /tmp/android-studio.tar.gz \
    && tar -zxvf /tmp/android-studio.tar.gz -C /usr/local \
    && rm /tmp/android-studio.tar.gz

# 環境変数の設定
ENV ANDROID_HOME /home/gitpod/android-sdk
ENV PATH $PATH:/usr/local/android-studio/bin
ENV GRADLE_USER_HOME /home/gitpod/.gradle

USER gitpod
