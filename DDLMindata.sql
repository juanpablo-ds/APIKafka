PGDMP                          {           mindata    15.3    15.3 	    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398    mindata    DATABASE     z   CREATE DATABASE mindata WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE mindata;
                postgres    false            �           0    0    DATABASE mindata    COMMENT     <   COMMENT ON DATABASE mindata IS 'bd para prueba de mindata';
                   postgres    false    3323                        2615    16399    data    SCHEMA        CREATE SCHEMA data;
    DROP SCHEMA data;
                postgres    false            �            1259    16403    search    TABLE     �   CREATE TABLE data.search (
    checkin date NOT NULL,
    checkout date NOT NULL,
    ages integer[] NOT NULL,
    hotelid character varying(200) NOT NULL,
    searchid character varying(200) NOT NULL
);
    DROP TABLE data.search;
       data         heap    postgres    false    6            �          0    16403    search 
   TABLE DATA           J   COPY data.search (checkin, checkout, ages, hotelid, searchid) FROM stdin;
    data          postgres    false    215          f           2606    16418    search search_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY data.search
    ADD CONSTRAINT search_pkey PRIMARY KEY (searchid);
 :   ALTER TABLE ONLY data.search DROP CONSTRAINT search_pkey;
       data            postgres    false    215            �   2  x���A�1E��g"�1�mΑ�F�\!��C�i�Hf�%��$����a�@ڼ�ߟ�n?譿�����Zg�����<�;9��st4����>�}�Гu�Ͱ97p��b4�CXgl;.�%j!%+���̊�p����BT����RxD`���|���c9.����dL�1��1�T6�}B�5��.�Y;9�� �b�ŗ�j夲�	�լ.XZ[�2�>|</'��{?!UA��8cLb�����f�u>^��!�gAF8m\6g��;�'�(9�����(a	/�M��3���3��u�����Xq9�섏C��V(���6�J�Iߢ�x.��3�� [VT�ʶ�l�����.�K԰2T���&�:*����tֵˊ�i��h��ԍ~ �gOn�>2�s����Z���n%�6�6��']�e�*#����ղ�k�K�s�Ї����l�B�RE�iP����O�;7��.3o;Ɏ�ڬ��
�kԞ����o�G����ԲZ?�j6�@2��
����/��*J����&��˺�
�s&enM��r��Mr��     