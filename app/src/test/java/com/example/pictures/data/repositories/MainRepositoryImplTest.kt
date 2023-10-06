package com.example.pictures.data.repositories

import com.example.pictures.data.datasource.local.PhotosLocalDatasource
import com.example.pictures.data.datasource.remote.PhotosRemoteDatasource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class MainRepositoryImplTest{

    @RelaxedMockK
    private lateinit var photoRemoteDatasource : PhotosRemoteDatasource
    @RelaxedMockK
    private lateinit var repository : MainRepositoryImpl
    @RelaxedMockK
    private lateinit var photoLocalDatasource : PhotosLocalDatasource

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        repository = MainRepositoryImpl(PhotosRemoteDatasource(),PhotosLocalDatasource())
    }

    @Test
    fun `when Api Return Empty Then Get Photo From Database`() = runBlocking {
        //given
        coEvery { photoRemoteDatasource.getPhotosApi() } returns emptyList()

        //when
        repository.getPhotos()

        //then
        coVerify(exactly = 1) { photoLocalDatasource.getPhotos() }
    }
}